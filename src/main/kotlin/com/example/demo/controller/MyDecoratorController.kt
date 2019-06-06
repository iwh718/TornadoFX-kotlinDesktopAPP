package com.example.demo.controller

import com.jfoenix.animation.alert.JFXAlertAnimation
import com.jfoenix.controls.JFXAlert
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDialogLayout
import javafx.scene.control.Label
import javafx.scene.paint.Color
import javafx.stage.Modality
import javafx.stage.Stage
import tornadofx.*

/**
 * decorator控制器
 */
class MyDecoratorController(private val stage: Stage):Controller(){
    /**
     * 拦截退出Modal
     */
    fun exitModal(title:String) =  JFXAlert<Unit>(stage).apply {
         val ly = JFXDialogLayout().apply lay@{
             setBody(Label("真的要退出吗？"))
             setActions(
                     JFXButton("确定").apply {
                         style {
                             backgroundColor += c("#3e763f")
                         }
                         textFill = Color.WHITE
                         action {
                             stage.close()
                         }

                     }
             )
             setHeading(Label(title))
         }
        this.animation = JFXAlertAnimation.CENTER_ANIMATION
        setSize(250.0, 100.0)
        this.setContent(ly)
        initModality(Modality.APPLICATION_MODAL)
    }

}