package com.example.demo.view.toolsView
import com.example.demo.modal.Books
import javafx.scene.Parent
import tornadofx.*
import java.awt.Color

/**
 * 图书搜索结果碎片视图层
 */
class ResultFragment(data:ArrayList<Books>):Fragment(title = "双击单元格复制"){
    override val root: Parent = stackpane {
        vbox {
            prefWidth = 600.0
            prefHeight = 400.0
            tableview(data.observable()) {
                smartResize()
                column("索取号",Books::number).makeEditable()
                column("书名", Books::name).makeEditable()

            }
        }
    }
}
