package com.example.demo.view

import com.example.demo.controller.TabController
import com.jfoenix.controls.JFXTabPane
import tornadofx.*
import java.awt.Insets


/**
 * Tab面板
 */
fun myTab(): JFXTabPane {
    return JFXTabPane().apply {
        //加载工具模块,tab随着drawer后初始化，如果tab控制器没有初始化则自动初始化
        tabs.addAll(find(TabController::class).start()).apply {
        }

    }
}