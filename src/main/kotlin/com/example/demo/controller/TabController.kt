package com.example.demo.controller

import com.example.demo.app.MyApp
import com.example.demo.modal.Books

import com.example.demo.view.MainView
import com.example.demo.view.toolsView.ResultFragment

import com.example.demo.view.toolsView.WebViewFragment
import com.example.demo.view.toolsView.bookSearch
import com.example.demo.view.toolsView.netChecker
import com.jfoenix.controls.JFXButton
import com.jfoenix.effects.JFXDepthManager
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.control.Tab
import javafx.scene.paint.Color
import javafx.scene.web.WebView
import javafx.stage.StageStyle
import tornadofx.*
import java.awt.Desktop
import java.io.StringReader
import java.lang.Exception
import java.net.URI
import javax.json.*


/**
 * tab控制器
 */
class TabController : Controller() {
    val searchName = SimpleStringProperty("")
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
                                setPreserveRatio(true)
                            }

                        }
                        this.add(JFXButton("期末成绩查询").apply {
                            action {
                                WebViewFragment("https://www.borebooks.top/classWD/wdClass.php").openModal()
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
                                netChecker().openModal(stageStyle = StageStyle.UNDECORATED)
                            }
                        })
                    }
                }
            }
        }
                , Tab("网易云热评").apply {
            content = WebView().apply {
                this.engine.load("https://www.borebooks.top/yb/wyy/index.php")
            }
        }, Tab("常用网址").apply {
            content = vbox {
                label {
                    text = "学校官网"
                    setOnMouseClicked {
                        Desktop.getDesktop().browse(URI("https://www.borebooks.top/yb/wyy/index.php"))
                    }
                }
                label {
                    text = "教务系统"

                }
                label { text = "图书系统" }
                label { text = "学校官网" }
                label { text = "学校官网" }
            }
        }
        )
    }

    fun searchBook(str: String? = null) {
        val bookUrl = "https://www.borebooks.top/wxx2.php?name="
        runAsync {
            var searchValue = str
            var res:Rest.Response? = null
            if (str.isNullOrBlank()) {
                searchValue = this@TabController.searchName.value
            }
            try {
               res = find(MainView::class).__api.get("$bookUrl$searchValue")
                res
            }catch (e:Exception){
                res

            }
        } ui {
            if (it == null) {
                println("获取失败！")

            } else {
                searchData.clear()
                it.list().forEach { it2 ->
                    searchData.add(Books(it2.asJsonArray()[0].asJsonObject()["name"].toString(), it2.asJsonArray()[0].asJsonObject()["number"].toString()))
                }
                ResultFragment(searchData).openModal()

            }
        }

    }
}