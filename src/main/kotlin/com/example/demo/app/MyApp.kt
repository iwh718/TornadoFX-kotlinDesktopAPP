package com.example.demo.app
import com.example.demo.view.myDecorator
import javafx.application.Application
import javafx.application.Application.launch
import javafx.application.Platform.setImplicitExit
import javafx.scene.Scene

import javafx.stage.Stage
import tornadofx.*
import tornadofx.FX.Companion.log
import tornadofx.FX.Companion.primaryStage
import java.awt.SystemColor.info

import java.io.IOException
import kotlin.concurrent.thread

/**
 * exe4j打包主函数
 */
fun main(args: Array<String>) {
    launch<MyApp>(args)
}

/**
 * kotlin-TornadoFx Demo
 * UI:Jfoenix
 * @author IWH
 */

class MyApp : App( ) {

    companion object {
        var staticStage: Stage by singleAssign()
        var staticApp: App by singleAssign()
    }
    override fun start(stage: Stage) {
        super.start(stage)
        staticApp = this
        staticStage = stage
        //设置场景大小,注入视图节点
        val scene = Scene(myDecorator(), 400.0, 500.0)
        //载入样式表
        scene.stylesheets.add("/css/jfoenix-components.css")
        stage.apply {
            //添加场景
            this.scene = scene
            //置顶
            isAlwaysOnTop = true
            //调出stage
            show()
        }

    }
}


