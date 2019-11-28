package com.example.demo.controller

import com.example.demo.app.MyApp
import com.example.demo.fragment.SearchResultFragment
import com.example.demo.modal.Books
import com.example.demo.modal.othersButtons

import com.example.demo.fragment.WebViewFragment
import com.example.demo.openUrlButton
import com.example.demo.smallView.*

import com.jfoenix.controls.JFXButton
import com.jfoenix.effects.JFXDepthManager
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Tab
import javafx.scene.paint.Color
import javafx.stage.Modality
import javafx.stage.StageStyle
import tornadofx.*
import java.lang.Exception
import java.util.*
import java.util.concurrent.CompletableFuture.runAsync


/**
 * tab控制器动态创建布局
 */
class TabController : Controller() {
    val searchName = SimpleStringProperty("")
    private val __api = Rest()
    private val searchData = arrayListOf<Books>()
    fun start(): ArrayList<Tab> {

        return arrayListOf(
                Tab("图书检索").apply {
                    content = bookSearch()

                }
                , Tab("校园服务").apply {
            content = vbox {
                style {
                    backgroundColor += c("#333333")
                }
                stackpane {

                    rectangle {

                        stackpaneConstraints {
                            margin = Insets(10.0)
                        }

                        alignment = Pos.CENTER
                        JFXDepthManager.setDepth(this, 1)
                        width = 350.0
                        height = 150.0
                        arcWidth = 10.0
                        arcHeight = 10.0
                        fill = c("#4d4d4d")
                    }
                    vbox {
                        alignment = Pos.CENTER
                        imageview {
                            this.image = MyApp.staticApp.resources.image("/icon/scores.png").apply {
                                fitWidth = 60.0
                                fitHeight = 60.0
                                //图片等比例
                                setPreserveRatio(true)
                            }

                        }
                        this.add(JFXButton("期末成绩查询").apply {
                            action {
                                //modality：none为不阻塞其它窗口
                                WebViewFragment("https://www.borebooks.top/classWD/wdClass.php").openModal(modality = Modality.NONE)
                            }
                            buttonType = JFXButton.ButtonType.RAISED
                            spacing = 10.0
                            style {
                                textFill = Color.WHITE
                                backgroundColor += c("#1a1a1a")
                            }
                        })

                    }


                }
                stackpane {

                    rectangle {
                        fill = c("#4d4d4d")
                        stackpaneConstraints {
                            margin = Insets(10.0)
                        }

                        JFXDepthManager.setDepth(this, 1)
                        width = 350.0
                        height = 150.0
                        arcWidth = 10.0
                        arcHeight = 10.0
                    }
                    vbox {
                        alignment = Pos.CENTER
                        imageview {
                            this.image = MyApp.staticApp.resources.image("/icon/net.png").apply {
                                fitWidth = 60.0
                                fitHeight = 60.0
                                setPreserveRatio(true)
                            }
                        }
                        this.add(JFXButton("校园网检测").apply {
                            buttonType = JFXButton.ButtonType.RAISED
                            spacing = 10.0
                            style {
                                textFill = Color.WHITE
                                backgroundColor += c("#1a1a1a")
                            }
                            action {
                                //网络检测
                                netChecker().openModal(stageStyle = StageStyle.UNDECORATED)
                            }
                        })
                    }
                }
            }
        }, Tab("常用网址").apply {
            content =
                gridpane {
                    useMaxWidth = true
                    style{
                        backgroundColor += c("#4d4d4d")
                    }
                    alignment = Pos.CENTER
                    vgap = 5.0
                    hgap  = 5.0
                    row {
                        addColumn(0, openUrlButton("学校官网",0,"http://www.wendaedu.com.cn/index.html"))
                        addColumn(1, openUrlButton("教务系统",0,"http://218.22.58.76:2346/home.aspx"))
                        addColumn(2, openUrlButton("图书系统",0,"http://172.16.1.43/wxjs/tmjs_form.asp"))
                    }
                    row {
                        addColumn(0, openUrlButton("易班网站",0,"http://www.yiban.cn/"))
                        addColumn(1, openUrlButton("四六级报考",0,"http://cet-bm.neea.cn/"))
                        addColumn(2, openUrlButton("教育考试网",0,"http://www.neea.edu.cn/"))
                    }
                    row {
                        addColumn(0, openUrlButton("文院公众号",1).apply {
                            onHover {
                                tooltip {
                                    graphic = imageview("/icon/wd.png")
                                }
                            }
                        })
                        addColumn(1, openUrlButton("校园V印",0,"http://www.vyin.com/public/toIndex.action"))
                        addColumn(2, openUrlButton("网络中心",0))
                    }
                }

        }
              /**  Tab("其它作品").apply {
                    content = flowpane{
                        style{
                            backgroundColor += c("#4d4d4d")
                        }
                        hgap = 5.0
                        vgap = 5.0
                        paddingAll = 10.0
                        //spacing = 10.0
                       for( i in othersButtons){
                           this += i.apply {
                               style{

                                   backgroundColor += c("#ffffff")
                               }
                           }
                       }
                    }

                }**/
        )
    }

    /**
     * API请求
     */
    fun searchBook(str: String? = null) {
        val bookUrl = "https://www.borebooks.top/wxx2.php?name="
        runAsync {
            var searchValue = str
            var res: Rest.Response? = null
            if (str.isNullOrBlank()) {
                searchValue = this@TabController.searchName.value
            }
            try {
                res = __api.get("$bookUrl$searchValue")
                res
            } catch (e: Exception) {
                res

            }
            //中缀调用,Ui不可以换行
        } ui {
            if (it == null) {
                println("获取失败！")

            } else {
                searchData.clear()
                it.list().forEach { it2 ->
                    searchData.add(Books(it2.asJsonArray()[0].asJsonObject()["name"].toString(), it2.asJsonArray()[0].asJsonObject()["number"].toString()))
                }
                SearchResultFragment(searchData).openModal(modality = Modality.NONE)

            }
        }

    }
}