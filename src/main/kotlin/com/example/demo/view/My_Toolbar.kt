package com.example.demo.view

import com.example.demo.app.MyApp
import com.example.demo.controller.ToolbarController
import com.example.demo.modal.SVG_OPTION

import com.example.demo.modal.SVG_logo
import com.jfoenix.controls.*
import javafx.geometry.Pos


import javafx.scene.control.Label
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import tornadofx.*



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
                            Label("交流群：1111"),
                            Label("版本：v1.0"))
                }
                maskType = JFXRippler.RipplerMask.CIRCLE
                ripplerFill = Color.WHITE
                this.setOnMouseClicked {
                    JFXPopup(menuList).show(this, JFXPopup.PopupVPosition.BOTTOM, JFXPopup.PopupHPosition.RIGHT)
                }
            },
            Label().apply {
                this.bind(find(ToolbarController::class).toolbarTitle)
            }
    )

    return jfxToolbar
}