package com.example.demo.app

import javafx.scene.text.FontWeight
import tornadofx.*

/**
 * App函数可以加入样式表
 */
class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
    }

    init {
       
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}