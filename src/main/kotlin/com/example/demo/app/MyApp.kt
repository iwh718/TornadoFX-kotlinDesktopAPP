package com.example.demo.app
import com.example.demo.view.myDecorator
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
 * kotlin-jfoenix Demo
 * @author IWH
 */

class MyApp : App() {

    companion object {
        var staticStage: Stage by singleAssign()
        var staticApp: App by singleAssign()

    }

    override fun start(stage: Stage) {
        super.start(stage)
        setImplicitExit(false)
        staticApp = this
        staticStage = stage
        val scene = Scene(myDecorator(), 400.0, 500.0)
        scene.stylesheets.add("/css/jfoenix-components.css")
        stage.apply {
            this.scene = scene
            //置顶
            isAlwaysOnTop = true
            show()
        }

    }


}


