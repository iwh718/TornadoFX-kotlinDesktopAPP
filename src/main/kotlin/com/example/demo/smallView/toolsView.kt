package com.example.demo

import com.example.demo.controller.TabController
import com.example.demo.fragment.WebViewFragment
import com.example.demo.modal.colorRandom
import com.jfoenix.controls.JFXButton
import javafx.scene.paint.Color
import tornadofx.*

        /**
         * 返回url按钮
         */
        fun openUrlButton(str:String,type:Int,url:String = ""): JFXButton {
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
                    else -> JFXButton.ButtonType.RAISED
                }
            }
        }

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
