package com.example.demo.controller


import com.example.demo.app.show
import com.example.demo.view.myDecorator
import com.example.demo.fragment.YbFragment
import com.jfoenix.controls.JFXSpinner
import com.jfoenix.controls.JFXToolbar
import com.sun.org.apache.bcel.internal.Repository.addClass

import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Scene

import javafx.scene.layout.StackPane
import javafx.stage.Stage
import tornadofx.*
import java.awt.Desktop

import java.lang.Exception
import java.net.URI
import java.time.LocalDate
import java.util.*


/**
 * ToolBar控制器
 */
class ToolbarController : Controller() {
    //单例网络对象
    private var netApi = Rest()
    //天气信息
    private var weatherInfo = LinkedHashMap<String, Any>().observable()
    //控制标题栏的文本
    var toolbarTitle = SimpleStringProperty("${LocalDate.now()}")
    //控制加载进度的显示
    private val loadFlag = SimpleBooleanProperty(true)



    init {
        //配置Rest的拦截器，超时
        netApi.engine.requestInterceptor = {
            (it as HttpURLRequest).connection.readTimeout = 3000
        }
    }
    //开始进行网络请求，获取天气
    fun start(toolbar: JFXToolbar) {
        //进度圈
        val sp = StackPane().apply {
            this.visibleProperty().bind(loadFlag)
            add(JFXSpinner().apply {
                addClass("materialDesign-blue", " first-spinner")
                startingAngle = -40.0
                scaleX = .3
                scaleY = .3

            })
            add(JFXSpinner().apply {
                addClass("materialDesign-white", "third-spinner")
                startingAngle = -120.0
                scaleX = .4
                scaleY = .4

            })
        }
        //添加进度圈到toolbar
        toolbar.leftItems.apply {
            add(sp)
        }
        //开始接口请求
        tornadofx.runAsync {
            var res: Rest.Response? = null

            try {
                res = netApi.get("https://www.tianqiapi.com/api/?version=v6&cityid=101220101")
            } catch (e: Exception) {
                ui {
                    show("请求失败,网络超时!!!", FX.primaryStage)
                    toolbarTitle.set("${weatherInfo["city"]} ${weatherInfo["tem"]}° PM2.5:${weatherInfo["air_pm25"]}}")
                    toolbarTitle.value = "${weatherInfo["city"]} ${weatherInfo["tem"]}° PM2.5:${weatherInfo["air_pm25"]}}"
                    toolbarTitle.value = "应用已离线..."
                }
                return@runAsync res
            }
            res
        //转到UI线程
        } ui {
            try {
                //判断status 等同于 status.code
                if (it!!.ok()) {
                    //解析JSON
                    val json = loadJsonObject(it.text()!!)
                    weatherInfo.putAll(json)
                    toolbarTitle.value = "${weatherInfo["city"]} ${weatherInfo["tem"]}° PM2.5 ${weatherInfo["air_pm25"]} ${weatherInfo["wea"]}".replace("\"","")
                    println("新的数据返回！")
                    //更新天气描述
                    toolbar.leftItems[1].tooltip( "${weatherInfo["air_tips"]}")
                    //取消加载
                    loadFlag.value = false
                  //  FX.log.info {
                     //   weatherInfo.values.toString()
                  //  }
                } else {
                    show("请求失败，接口异常！:${it.status}", FX.primaryStage)
                }
            } catch (e: Exception) {
                show("请求失败,网络超时!", FX.primaryStage)
            } finally {
                it?.close()
            }
        }
    }

    /**
     * 汉堡菜单的选项
     */
    fun browserUrl(type:Int){
        when(type){
            //添加QQ群
            0 -> Desktop.getDesktop().browse(URI("https://shang.qq.com/wpa/qunwpa?idkey=c9944d479e25db7c874da7b4c18ff1d5a0484088ebd806fdadfcd522ed9a28c4"))
            1 -> {
                val stageTmp = Stage()
                stageTmp.scene = Scene(myDecorator(stageTmp, YbFragment().root, "易班文章助手:目前仅支持易班话题文章:--by_iwh_2019.06.04"), 700.0, 600.0).apply {
                    this.stylesheets.add("/css/jfoenix-components.css")
                }
                stageTmp.show()
            }
            2 ->  Desktop.getDesktop().browse(URI("https://blog.csdn.net/u010913414?t=1"))
            3 ->  Desktop.getDesktop().browse(URI("https://github.com/iwh718?tab=repositories"))
        }
    }
}