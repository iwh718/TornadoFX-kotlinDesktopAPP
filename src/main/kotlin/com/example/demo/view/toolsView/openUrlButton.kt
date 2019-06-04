package com.example.demo.view.toolsView

import com.jfoenix.controls.JFXButton
import javafx.scene.paint.Color
import tornadofx.*
import java.awt.Desktop
import java.net.URI

/**
 * 返回url按钮
 */
fun openUrlButton(str:String,type:Int,url:String = ""):JFXButton{
    return JFXButton(str).apply {
        action {
            if(url.isNotBlank()) WebViewFragment(url).openModal()

        }
        useMaxWidth = true
        style{
            textFill = Color.WHITE
            backgroundColor += c("#39b1ff")
        }
        buttonType = when(type){
            0 -> JFXButton.ButtonType.RAISED
            1 -> JFXButton.ButtonType.FLAT
            else ->JFXButton.ButtonType.RAISED
        }
    }
}