package com.example.demo.view

import com.example.demo.controller.ToolbarController

import com.jfoenix.controls.*


import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import tornadofx.*


/**
 * 工具栏视图层
 */

fun myToolbar(): JFXToolbar {
    val toolbarController =    find( ToolbarController::class)
    val jfxToolbar = JFXToolbar()
    jfxToolbar.setLeftItems(
            JFXRippler(
                    StackPane(
                            //汉堡菜单
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
                    items.addAll(Label("开发者：IWH"),
                            Label("交流群：778399961").apply {
                                setOnMouseClicked {
                                    toolbarController.browserUrl(0)
                                }
                            },
                            Label("易班工具").apply {
                                setOnMouseClicked {
                                    toolbarController.browserUrl(1)
                                }
                            },
                            Label("我的CSDN博客").apply {
                                setOnMouseClicked {
                                    toolbarController.browserUrl(2)
                                }
                            },
                            Label("我的GitHub").apply {
                                setOnMouseClicked {
                                    toolbarController.browserUrl(3)
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
                    //弹出菜单
                    JFXPopup(menuList).show(this, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT)
                }
            },
            Label().apply {
                //将toolbar的标题与控制器双向绑定
                this.bind(toolbarController.toolbarTitle)
            }
    )
    //返回ToolBar
    return jfxToolbar
}