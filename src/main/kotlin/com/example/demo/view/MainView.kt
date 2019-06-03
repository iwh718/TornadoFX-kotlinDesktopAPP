package com.example.demo.view


import com.example.demo.app.myPopup
import com.example.demo.controller.MainViewController
import com.example.demo.controller.TabController
import com.example.demo.controller.ToolbarController
import com.jfoenix.controls.*

import tornadofx.*

/**
 * 主视窗
 * 所有的基础view都是单例
 */
class MainView : View("文达校园小工具") {


    var myToolbar: JFXToolbar by singleAssign()
    var __myPopup: JFXPopup by singleAssign()
    val __api: Rest by inject()
    val mainViewController: MainViewController by inject()
    val toolbarController: ToolbarController by inject()


    override val root = stackpane {

        primaryStage.apply {
            minWidth = 400.0
            minHeight = 500.0
            maxWidth = 400.0
            maxHeight = 500.0

            //关闭用户自定义宽度
            isResizable = false


        }
    }

    init {



        myToolbar = myToolbar()
        __myPopup = myPopup()
        mainViewController.start()
        toolbarController.start(myToolbar)
        with(root) {
            borderpane {
                style {
                    spacing = 20.px
                }
                center =  myTab()
                top = myToolbar
                }


            }
        }


    }


















