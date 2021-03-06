package com.example.demo.fragment

import com.example.demo.modal.SVG_logo
import javafx.beans.property.SimpleStringProperty
import javafx.scene.Parent
import tornadofx.*

/**
 * 通用webView视图层
 */
class WebViewFragment( url:String):Fragment(url){
    private val u = SimpleStringProperty(url)
    override val root: Parent = webview {
        this.engine.isJavaScriptEnabled = true
        this.svgicon(SVG_logo)
        //agent手机端
      //  this.engine.userAgent = "Mozilla/5.0 (Linux; Android 4.4.4; HM NOTE 1LTEW Build/KTU84P) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/33.0.0.0 Mobile Safari/537.36 MicroMessenger/6.0.0.54_r849063.501 NetType/WIFI"
        this.engine.load(url)
        println(this.engine.location)
    }
}