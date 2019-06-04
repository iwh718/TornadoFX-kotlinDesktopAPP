package com.example.demo.view

import com.example.demo.app.MyApp
import com.example.demo.modal.SVG_logo
import com.jfoenix.animation.alert.JFXAlertAnimation
import com.jfoenix.controls.JFXAlert
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDecorator
import com.jfoenix.controls.JFXDialogLayout
import javafx.scene.Node
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.stage.Modality
import javafx.stage.Stage
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
fun myDecorator(stage: Stage = MyApp.staticStage,view: Node = find(MainView::class).root ,myTitle:String = "生活助手v1.0"): JFXDecorator {
    return   JFXDecorator(stage, view).apply dec@{
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
                            stage.close()
                            /**  if(SystemTray.getSystemTray().trayIcons.isNotEmpty()){
                                 MyApp.staticStage.close()
                                return@action
                            }

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
                                      runLater{
                                            MyApp.staticStage.show()
                                        }
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
    **/
                        }


                    })
            setHeading(Label(myTitle))
        }

        this.setOnCloseButtonAction {
            //拦截退出事件
            JFXAlert<Unit>(stage).apply {
                this.animation = JFXAlertAnimation.CENTER_ANIMATION
                setSize(250.0, 100.0)
                this.setContent(layout)
                initModality(Modality.APPLICATION_MODAL)
            }.show()
        }

        setGraphic(SVGIcon(color = Color.WHITE, svgShape = SVG_logo).apply {
            this.rotate = -180.0
        })
        title = myTitle

    }




}