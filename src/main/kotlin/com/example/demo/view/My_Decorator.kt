package com.example.demo.view

import com.example.demo.app.MyApp
import com.example.demo.modal.SVG_logo
import com.jfoenix.animation.alert.JFXAlertAnimation
import com.jfoenix.controls.JFXAlert
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDecorator
import com.jfoenix.controls.JFXDialogLayout
import com.jfoenix.svg.SVGGlyph
import com.sun.jndi.toolkit.url.Uri
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.stage.Modality
import tornadofx.*
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.image.BufferedImage
import java.io.File
import java.lang.Exception
import java.net.URL
import javax.imageio.ImageIO
import javax.swing.ImageIcon
import kotlin.math.log

/**
 * 标题栏
 */
fun myDecorator(): JFXDecorator {
    return   JFXDecorator(MyApp.staticStage, find(MainView::class).root).apply dec@{
        //退出对话框布局
        val layout = JFXDialogLayout().apply lay@{
            setBody(Label("真的要退出吗？"))
            setActions(
                    JFXButton("确定").apply {
                        style {
                            backgroundColor += c("#3e763f")
                        }
                        textFill = Color.WHITE
                        action {
                            //MyApp.staticStage.close()
                            val imgRes = MyApp.staticApp.resources.url("/imgs/tray.png")
                            val tray = ImageIcon(imgRes).image
                            val trayIcon = TrayIcon(tray)
                            trayIcon.toolTip = "生活助手"
                            trayIcon.addMouseListener(object : MouseAdapter() {
                                override fun mouseClicked(e: MouseEvent?) {
                                    println("你点击了托盘图标")
                                }
                            })
                            trayIcon.popupMenu = PopupMenu().apply {
                                add(MenuItem("打开").apply {
                                    addActionListener {
                                        println("你点击打开！")
                                        MyApp.staticStage.show()
                                    }
                                })
                                add(MenuItem("退出").apply {
                                    addActionListener {
                                        System.exit(0)
                                    }
                                })
                            }
                            try {
                                SystemTray.getSystemTray().add(trayIcon)
                            } catch (e: Exception) {
                                println(e.toString())
                            } finally {

                                this@dec.isVisible = false
                                MyApp.staticStage.hide()
                            }

                        }


                    })
            setHeading(Label("生活助手"))
        }

        this.setOnCloseButtonAction {
            //拦截退出事件
            JFXAlert<Unit>(MyApp.staticStage).apply {
                this.animation = JFXAlertAnimation.BOTTOM_ANIMATION
                setSize(450.0, 200.0)
                this.setContent(layout)
                initModality(Modality.APPLICATION_MODAL)
            }.show()
        }

        setGraphic(SVGIcon(color = Color.WHITE, svgShape = SVG_logo).apply {
            this.rotate = -180.0
        })
        title = "生活助手"

    }




}