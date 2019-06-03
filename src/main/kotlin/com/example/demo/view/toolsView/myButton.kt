package com.example.demo.view.toolsView

import com.example.demo.controller.TabController
import com.jfoenix.controls.JFXButton
import javafx.scene.paint.Color
import tornadofx.*

fun myButton(str:String):JFXButton{
    return JFXButton(str).apply {
        action {

            find(TabController::class).searchBook(str)
        }

       lineSpacing = 5.0
        style{
            paddingAll = 5.0
            spacing = 5.px
            backgroundColor += c("#fff")

        }
        buttonType = JFXButton.ButtonType.RAISED
    }
}