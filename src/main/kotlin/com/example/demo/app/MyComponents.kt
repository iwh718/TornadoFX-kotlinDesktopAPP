package com.example.demo.app

import com.jfoenix.animation.alert.JFXAlertAnimation
import com.jfoenix.controls.*
import com.jfoenix.svg.SVGGlyph
import com.sun.org.apache.bcel.internal.Repository.addClass
import javafx.scene.control.Label
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.Pane
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*

/**
 * 警告模态窗口
 */
fun show(str: String = "hello", viewStage: Stage) {
    JFXAlert<Unit>(viewStage).apply {

        this.animation = JFXAlertAnimation.BOTTOM_ANIMATION
        this.isOverlayClose = true
        setSize(450.0, 200.0)
        this.setContent(Label( str ))
    }.showAndWait()
}
