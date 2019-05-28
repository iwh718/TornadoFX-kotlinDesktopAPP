package com.example.demo.view

import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXDrawer
import com.jfoenix.controls.JFXScrollPane
import com.jfoenix.controls.JFXSlider
import javafx.scene.layout.StackPane
import tornadofx.*
import tornadofx.Stylesheet.Companion.label

/**
 * 侧边菜单
 */
fun myDrawer():JFXDrawer{
    val leftDrawer = JFXDrawer()
    val leftDrawerPane = StackPane()
    leftDrawer.setContent(
            myTab()
    )
    leftDrawerPane.styleClass.add("red-400")
    leftDrawerPane.children.add(
            JFXScrollPane().apply {
                vbox {
                    add(label("顶部项"))
                    add(label("菜单项"))
                    add(label("设置项"))
                    add(label("关于项"))
                }
            }
    )
    leftDrawer.setSidePane(leftDrawerPane)
    leftDrawer.defaultDrawerSize = 200.0
    leftDrawer.isResizeContent = true  //自动适应
    leftDrawer.isOverLayVisible = false//覆盖子布局
    leftDrawer.isResizableOnDrag = true//拖拽开启
    leftDrawer.show()

    return  leftDrawer
}