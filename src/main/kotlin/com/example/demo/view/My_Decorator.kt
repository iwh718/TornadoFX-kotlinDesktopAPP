package com.example.demo.view

import com.example.demo.app.MainView
import com.example.demo.app.MyApp
import com.example.demo.controller.MyDecoratorController
import com.example.demo.modal.SVG_logo
import com.jfoenix.controls.JFXDecorator
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.stage.Stage
import tornadofx.*

/**
 * 标题栏视图层
 * 注入视图：MainView::class
 */
fun myDecorator(stage: Stage = MyApp.staticStage, view: Node = find(MainView::class).root, myTitle: String = "生活助手v1.0"): JFXDecorator {
    return JFXDecorator(stage, view).apply dec@{
        this.setOnCloseButtonAction {
            //拦截退出事件
            MyDecoratorController(stage).exitModal(myTitle).show()
        }
        //设置Logo
        setGraphic(SVGIcon(color = Color.WHITE, svgShape = SVG_logo).apply {
            this.rotate = -180.0
        })
        //设置标题
        title = myTitle

    }


}