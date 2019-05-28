package com.example.demo.controller


import com.example.demo.app.show
import com.jfoenix.controls.JFXSpinner
import com.jfoenix.controls.JFXToolbar
import javafx.scene.layout.HBox
import javafx.scene.layout.StackPane
import tornadofx.*
import java.lang.Exception


class ToolbarController : Controller() {

    var netApi = Rest()
    var weatherInfo = LinkedHashMap<String, Any>().observable()
    init {
       netApi.engine.requestInterceptor = {
           (it as HttpURLRequest).connection.readTimeout  = 3000
       }
    }
    fun start( toolbar:JFXToolbar) {
        val sp =    StackPane().apply {

        add(JFXSpinner().apply {
            addClass("materialDesign-red"," first-spinner")
            startingAngle = -40.0
            scaleX = .8
            scaleY = .8

        })

           add( JFXSpinner().apply {
               addClass("materialDesign-cyan","third-spinner")
               startingAngle = -120.0
               scaleX = .6
               scaleY = .6

           })
           add( JFXSpinner().apply {
               addClass("materialDesign-green","fourth-spinner")
               startingAngle = -150.0
               scaleX = .4
               scaleY = .4

           })
           add( JFXSpinner().apply {
               addClass("materialDesign-yellow"," fifth-spinner")
               startingAngle = -180.0
               scaleX = .2
               scaleY = .2

           })


       }
      toolbar.leftItems.apply {
            add(sp)
        }
        tornadofx.runAsync {
            var res:Rest.Response? = null

               try {
                 res =  netApi.get("https://www.tianqiapi.com/api/?version=v6&cityid=101220101")
              }catch (e:Exception){
                   ui {
                       show("请求失败,网络超时!", FX.primaryStage)
                       toolbar.leftItems[1].replaceWith(label("应用离线中..."))
                       toolbar.leftItems[2].hide()
                       config.set("netFlag","false")
                   }
                   return@runAsync res
              }
           res

        } ui {
        try {
            if (it!!.ok()) {
                val json = loadJsonObject(it.text()!!)
                weatherInfo.putAll(json)
                toolbar.leftItems[1].text("城市：${weatherInfo["city"]} 温度： ${weatherInfo["tem"]}PM2.5:${weatherInfo["air_pm25"]}}"){
                    tooltip("${json["air_tips"]}")
                }
                FX.log.info {
                    weatherInfo.values.toString()
                }
            } else {
                show("请求失败，接口异常！:${it.status}", FX.primaryStage)
            }
        }catch (e:Exception){
            show("请求失败,网络超时!", FX.primaryStage)
        }finally {
            it?.close()
        }
        }
    }
}