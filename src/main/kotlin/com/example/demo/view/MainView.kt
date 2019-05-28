package com.example.demo.view



import com.example.demo.app.MyApp
import com.example.demo.app.myPopup
import com.example.demo.app.show
import com.example.demo.controller.MainViewController
import com.example.demo.controller.ToolbarController
import com.jfoenix.animation.alert.JFXAlertAnimation
import com.jfoenix.controls.*
import javafx.geometry.Insets
import javafx.scene.Scene

import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*


import javafx.scene.control.Label
import javafx.scene.control.ScrollPane
import javafx.stage.Window
import jdk.nashorn.internal.objects.Global
import sun.awt.WindowClosingListener
import tornadofx.Stylesheet.Companion.left
import javax.json.JsonObject
import javax.management.Notification


class MainView : View() {

    private var __myDrawer: JFXDrawer by singleAssign()
    private var __myToolbar: JFXToolbar by singleAssign()
    private var __myPopup: JFXPopup by singleAssign()
    private val __api: Rest by inject()
    private val __MainViewController:MainViewController by inject()
    private val __ToolbarController:ToolbarController by inject()


    override val root = stackpane {

        primaryStage.apply {
            minWidth = 500.0
            minHeight = 700.0
            maxWidth = 500.0
            maxHeight = 700.0
            isResizable = false


        }
    }

    init {


        __myDrawer = myDrawer()
        __myToolbar = myToolbar()
        __myPopup = myPopup()
        __MainViewController.start()
        __ToolbarController.start(__myToolbar)
        with(root) {
            borderpane {
                style {
                    spacing = 20.px
                }
                center = __myDrawer
                top = __myToolbar.apply {
                    this.leftItems[0].apply {
                        this.setOnMouseClicked {
                            if (__myDrawer.isClosed) {
                                __myDrawer.open()
                            } else {
                                __myDrawer.close()
                            }

                        }
                    }

                }


            }
        }



    }

}

















