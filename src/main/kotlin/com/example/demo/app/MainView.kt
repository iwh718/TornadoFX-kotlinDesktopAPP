package com.example.demo.app


import com.example.demo.controller.ToolbarController
import com.example.demo.view.myTab
import com.example.demo.view.myToolbar
import com.jfoenix.controls.*

import tornadofx.*

/**
 * 主视窗
 */
class MainView : View("文达校园小工具") {

    //ToolBar单例
    var myToolbar: JFXToolbar by singleAssign()
    //ToolBar菜单单例
    var __myPopup: JFXPopup by singleAssign()
    //根节点
    override val root = stackpane {
        //设置主stage
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
        //初始化工具栏
        myToolbar = myToolbar()
        //获取天气接口
        find(ToolbarController::class).start(myToolbar)
        //根视图
        with(root) {
            borderpane {
                //中心为tab面板
                center = myTab()
                top = myToolbar
                }

            }
        }
    }

















