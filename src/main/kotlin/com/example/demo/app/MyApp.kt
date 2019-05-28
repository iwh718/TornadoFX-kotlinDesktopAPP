package com.example.demo.app

import com.example.demo.modal.SVG_logo
import com.example.demo.view.MainView
import com.example.demo.view.myDecorator
import com.jfoenix.animation.alert.JFXAlertAnimation
import com.jfoenix.controls.JFXAlert
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDecorator
import com.jfoenix.svg.SVGGlyph
import com.jfoenix.svg.SVGGlyphLoader
import javafx.collections.FXCollections
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.stage.Stage
import javafx.stage.Window
import javafx.util.Duration
import sun.awt.WindowClosingListener
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

    companion object{
        var staticStage:Stage by singleAssign()
        var staticApp:App by singleAssign()

    }
    override fun start(stage: Stage) {
        super.start(stage)
        staticApp = this
        staticStage = stage
        val scene = Scene(myDecorator(), 500.0, 700.0)
        scene.stylesheets.add("/css/jfoenix-components.css")
        stage.apply {
            this.scene = scene
            //置顶
            isAlwaysOnTop = true
            show()
        }

    }








}


