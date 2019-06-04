package com.example.demo.view

import com.example.demo.app.MyApp
import com.example.demo.controller.ToolbarController
import com.example.demo.modal.SVG_OPTION

import com.example.demo.modal.SVG_logo
import com.example.demo.view.toolsView.YbTools
import com.jfoenix.controls.*
import com.sun.jndi.toolkit.url.Uri
import javafx.geometry.Pos
import javafx.scene.Scene


import javafx.scene.control.Label
import javafx.scene.control.Separator
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import sun.security.krb5.internal.crypto.Des
import tornadofx.*
import java.awt.Desktop
import java.net.URI


/**
 * 工具栏
 */

fun myToolbar(): JFXToolbar {
    val jfxToolbar = JFXToolbar()
    jfxToolbar.setLeftItems(
            JFXRippler(
                    StackPane(
                            JFXHamburger().apply {
                                this.children.style {
                                    backgroundColor += Color.WHITE
                                }

                                id = "titleBurger"
                                this.style {
                                    scaleX = .7
                                    scaleY = .7
                                }

                            }
                    ).apply {
                        id = "titleBurgerContainer"
                    }
            ).apply ripple@{
                val menuList = JFXListView<Label>().apply {
                    items.addAll(Label("开发者：IWH").apply {

                        this.setOnMouseClicked {
                            JFXSnackbar(this@ripple).enqueue(JFXSnackbar.SnackbarEvent(label("hello！")))
                        }
                    },
                            Label("交流群：778399961").apply {
                                setOnMouseClicked {
                                    Desktop.getDesktop().browse(URI("https://shang.qq.com/wpa/qunwpa?idkey=c9944d479e25db7c874da7b4c18ff1d5a0484088ebd806fdadfcd522ed9a28c4"))
                                }
                            },
                            Label("易班工具").apply {
                                setOnMouseClicked {
                                    val stageTmp = Stage()
                                    stageTmp.scene = Scene(myDecorator(stageTmp, YbTools().root,"易班文章助手:目前仅支持易班话题文章:--by_iwh_2019.06.04"),700.0,600.0).apply {
                                        this.stylesheets.add("/css/jfoenix-components.css")
                                    }
                                   stageTmp.show()
                                }
                            },
                            Label("我的CSDN博客").apply {
                                setOnMouseClicked {
                                    Desktop.getDesktop().browse(URI("https://blog.csdn.net/u010913414?t=1"))
                                }
                            },
                            Label("我的GitHub").apply {
                                setOnMouseClicked {
                                    Desktop.getDesktop().browse(URI("https://github.com/iwh718?tab=repositories"))
                                }
                            },
                            Label("我的公众号:悬浮查看").apply {
                                onHover {
                                    tooltip {
                                        graphic = imageview("/icon/wd.png")
                                    }
                                }
                            }
                          )
                }
                maskType = JFXRippler.RipplerMask.CIRCLE
                ripplerFill = Color.WHITE
                this.setOnMouseClicked {
                    JFXPopup(menuList).show(this, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT)
                }
            },
            Label().apply {
                this.bind(find(ToolbarController::class).toolbarTitle)
            }
    )

    return jfxToolbar
}