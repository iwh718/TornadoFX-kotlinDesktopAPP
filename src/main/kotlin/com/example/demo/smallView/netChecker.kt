package com.example.demo.smallView

import com.example.demo.app.MyApp
import com.example.demo.fragment.WebViewFragment
import com.jfoenix.animation.alert.JFXAlertAnimation
import com.jfoenix.controls.*
import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty

import javafx.geometry.Pos
import javafx.scene.Parent

import tornadofx.*

import java.lang.Exception

/**
 * 网络检测控制器
 */
class NetCheckerController:Controller(){
    val loadFlag = SimpleDoubleProperty(0.0)
    val netTips = SimpleStringProperty("1.开始检测文达校园网络状态")
    var api:Rest by singleAssign()

     fun ck(){
       api = Rest().apply {
             this.engine.requestInterceptor = {
                 (it as HttpURLRequest).connection.readTimeout = 3000
             }
         }
         var res: Rest.Response? = null
         var res2: Rest.Response? = null

         tornadofx.runAsync {

             try {
                 res = api.get("http://172.16.1.101/portalcloud/page/3/PC/chn/Login.html?p=login")
                 res
             } catch (e: Exception) {
                 res

             }

         } ui {
             netTips.value += "\n2.尝试访问校内网络中...."
             loadFlag.value = .2
             if (it == null) {
                 loadFlag.value = -1.0
                 netTips.value += "\n3.访问校园网失败"
                 netTips.value += "\n4.您当前无网络或未处于校园网状态！"

             } else if (it.ok()) {
                 loadFlag.value = -1.0
                 netTips.value += "\n3.访问校园网成功！"
                 netTips.value += "\n4.开始检测外部网络状态"
                 //继续判断是否登录
                 tornadofx.runAsync {
                     try {
                         res2 = api.get("https://www.baidu.com")
                         res2
                     } catch (e: Exception) {
                         res2
                     }
                 } ui { it2 ->
                     loadFlag.value = .7
                     if (it2 == null) {
                         loadFlag.value = -1.0
                         netTips.value += "\n5.外网访问失败"
                         JFXAlert<Unit>(MyApp.staticStage).apply {
                             animation = JFXAlertAnimation.BOTTOM_ANIMATION
                             setContent(JFXDialogLayout().apply {
                                 this.setHeading(label { text = "校园网络被断开，是否打开登录！" })
                                 this.setActions(JFXButton("登录").apply {
                                     action {
                                         WebViewFragment("http://172.16.1.43/dzjs/jhcx.asp").openModal()
                                     }
                                 })
                             })
                         }.showAndWait()
                     } else if (it2.ok()) {
                         loadFlag.value = 1.0
                         netTips.value += "\n5.访问外网成功，网络正常！"

                     }

                 }
             }


         }
    }
}
/**
 * 网络检测视图
 */
fun netChecker(): Fragment {
    val netCheckerController  = NetCheckerController()
    //开始执行请求
    netCheckerController.ck()
    return object : Fragment(title = "校园网检测") {

        var netFragment: Fragment = this

        override val root: Parent = vbox {

            prefWidth = 300.0
            style {
                backgroundColor += c("#4d4d4d")
            }
            prefHeight = 250.0
            vbox {
                fitToParentHeight()
                style {
                    backgroundColor += c("#ffffff")
                    backgroundRadius += CssBox(10.px, 10.px, 10.px, 10.px)
                }
                alignment = Pos.CENTER
                spacing = 5.0
                paddingTop = 10.0
                paddingLeft = 10.0
                paddingRight = 10.0
                label {
                    bind(netCheckerController.netTips)
                }
                progressindicator {
                    this.progressProperty().bind(netCheckerController.loadFlag)

                }
                this += JFXButton("关闭").apply {

                    spacing = 10.0
                    buttonType = JFXButton.ButtonType.RAISED
                    style {
                        backgroundColor += c("#1a1a1a")
                        textFill = c("#ffffff")
                    }
                    action {
                        //关闭检测窗口
                        netFragment.close()
                    }
                }

            }

        }
    }


}