package com.example.demo.view

import com.example.demo.modal.SVG_OPTION

import com.example.demo.modal.SVG_logo
import com.jfoenix.controls.JFXHamburger
import com.jfoenix.controls.JFXRippler
import com.jfoenix.controls.JFXToolbar
import com.jfoenix.svg.SVGGlyph
import javafx.scene.control.Label
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import tornadofx.*
import java.time.LocalDate

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
            ).apply {
                maskType = JFXRippler.RipplerMask.CIRCLE
                ripplerFill = Color.WHITE
            },
            Label("${LocalDate.now()}")
    )
    jfxToolbar.setRightItems(
            JFXRippler(
                    StackPane(
                            SVGIcon(color = Color.WHITE, svgShape = SVG_OPTION)
                    )
            ).apply {
                maskType = JFXRippler.RipplerMask.CIRCLE
                ripplerFill = Color.WHITE
            }

    )
    return jfxToolbar
}