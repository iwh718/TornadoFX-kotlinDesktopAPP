package com.example.demo.view.toolsView

import com.example.demo.modal.SVG_logo
import com.jfoenix.controls.JFXButton
import com.jfoenix.controls.JFXSnackbar
import javafx.scene.Parent
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.control.TextField
import javafx.scene.input.KeyCode
import javafx.scene.paint.Color
import javafx.scene.web.HTMLEditor
import javafx.scene.web.WebView
import tornadofx.*

import java.lang.Exception



/**
 * 易班文本工具
 * @author IWH
 * time:2019.06.03
 */
class YbTools : Fragment("易班文章助手:目前仅支持易班话题文章:--by_iwh_2019.06.04", SVGIcon(SVG_logo)) {
    private var ybText: TextField by singleAssign()
    private var ybWeb: WebView by singleAssign()

    private var ybEditor: HTMLEditor by singleAssign()
    override val root: Parent = stackpane {
        vbox {

            fitToParentWidth()
            spacing = 5.0
            fitToParentHeight()
            prefHeight = 800.0
            prefWidth = 900.0
            ybText = textfield {
                fitToParentWidth()
                spacing = 10.0
                useMaxWidth = true
                promptText = "输入易班文章链接后，回车开始预览..."

                setOnKeyPressed {
                    if (it.code == KeyCode.ENTER) {
                        ybWeb.engine.load(text)
                    }
                }
            }
            hbox {
                spacing = 5.0
                paddingAll = 5.0
                this += JFXButton("开始复制").apply {

                    buttonType = JFXButton.ButtonType.RAISED
                    style {
                        backgroundColor += c("#39b1ff")
                        textFill = Color.WHITE
                    }
                    action {
                        val scriptStr = "$('.detail-forum-text').html();"
                        val tmp = ybWeb.engine.executeScript(scriptStr) as String
                        ybEditor.htmlText = "<div style = 'margin:0 auto;clear:both;'>$tmp</div>"

                    }
                }
                this += JFXButton("清空编辑器").apply {
                    buttonType = JFXButton.ButtonType.RAISED
                    style {
                        textFill = Color.WHITE
                        backgroundColor += c("#ff681d")
                    }
                    action {
                        ybEditor.htmlText = ""
                        ybText.text = ""
                    }
                }
                this += JFXButton("请等待右侧预览页面加载完成再复制！").apply {
                    style {
                        textFill = Color.WHITE
                        backgroundColor += c("#000", 0.69)
                    }

                }
            }
            splitpane {
                fitToParentHeight()
                ybEditor = htmleditor {
                    fitToParentHeight()
                    this.htmlText = "<h1>如何使用：</h2><br>" +
                            "1.你可以使用两种方式复制指定文章的样式<br>" +
                            "（1） 在上方输入框输入你要复制的文章链接，回车后，右侧页面开始预览你输入的地址内容，你可以点击开始复制，这个区域是显示复制后的预览，你可以直接在此修改，或去易班发布话题的编辑器修改。<br>" +
                            "（2） 第二种方法是直接在右侧页面寻找你要复制的文章，右键复制，之后你点击开始复制即可。<br>" +
                            "2.注意：请等待右侧图文预览加载完成，再执行复制操作，不然会出现图文不全！<br>" +
                            "3.如何放到易班编辑器：当这个区域出现完成的内容后，右键复制完整样式，打开易班网站的编辑器，Ctrl+V 复制过去，这个时候会有错位的，你需要执行以下一个操作： Ctrl + A 全选，点击易班编辑器的居中排版。然后预览即可。<br><br> "
                    this.contextMenu = ContextMenu(MenuItem("清空内容").apply {
                        action {
                            htmlText = "<div></div>"
                        }
                    }, MenuItem("复制完整样式").apply {
                        action {
                            try {

                                JFXSnackbar(this@stackpane).enqueue(JFXSnackbar.SnackbarEvent(label("请手动复制：Ctrl+A Ctrl + C")))
                            } catch (e: Exception) {
                                JFXSnackbar(this@stackpane).enqueue(JFXSnackbar.SnackbarEvent(label("系统不支持，请手动复制：Ctrl + A 全选 Ctrl + C 复制！")))
                            }

                        }
                    })


                }
                ybWeb = webview {
                    this.engine.load("http://www.yiban.cn/")
                    this.engine.userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.131 Safari/537.36"
                    contextMenu = ContextMenu(MenuItem("复制该文章").apply {
                        action {
                            ybText.text = this@webview.engine.location
                            val scriptStr = "$('.detail-forum-text').html();"
                            ybEditor.htmlText = ybWeb.engine.executeScript(scriptStr) as String
                        }
                    },MenuItem("回到上一页").apply {
                        action {
                            ybWeb.engine.history.go(-1)
                        }
                    })
                    fitToParentHeight()

                }

            }
        }
    }
}