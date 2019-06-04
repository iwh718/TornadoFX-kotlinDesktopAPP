package com.example.demo.view.toolsView

import com.example.demo.controller.TabController
import com.example.demo.modal.colorRandom

import com.jfoenix.controls.JFXButton
import javafx.scene.paint.Color
import tornadofx.*

/**
 * 生成图书快捷搜索按钮
 */
fun myButton(str:String,type:Int = 0):JFXButton{
    return JFXButton(str).apply {
        action {

            if(type == 0) find(TabController::class).searchBook(str)
        }

       lineSpacing = 5.0
        style{
            paddingAll = 5.0
            textFill = Color.WHITE
            spacing = 5.px
            //println(Math.ceil(Math.random()*100).toInt()/10)
           backgroundColor += colorRandom[Math.ceil(Math.random()*100).toInt()/10]

        }
        buttonType = JFXButton.ButtonType.RAISED
    }
}