package com.example.demo.view


import com.example.demo.app.MyApp
import com.example.demo.app.show
import com.example.demo.modal.SVG_logo
import com.jfoenix.controls.JFXSpinner
import com.jfoenix.controls.JFXTabPane

import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.control.Tab
import javafx.scene.layout.StackPane
import javafx.scene.paint.Color


import tornadofx.*
import java.awt.Image
import java.awt.SystemTray
import java.awt.TrayIcon
import javax.imageio.ImageIO


/**
 * Tab面板
 */
fun myTab():JFXTabPane{
  return  JFXTabPane().apply {
      val sp =    StackPane().apply {
            style{
                minWidth = 100.px
                minHeight = 100.px

            }
          add(JFXSpinner().apply {
              addClass("materialDesign-red"," first-spinner")
              startingAngle = -40.0
              scaleX = .9
              scaleY = .9

          })
          add( JFXSpinner().apply {
              addClass("materialDesign-blue"," second-spinner")
              startingAngle = -90.0
              scaleX = .8
              scaleY = .8

          })
          add( JFXSpinner().apply {
              addClass("materialDesign-cyan","third-spinner")
              startingAngle = -120.0
              scaleX = .7
              scaleY = .7

          })
          add( JFXSpinner().apply {
              addClass("materialDesign-green","fourth-spinner")
              startingAngle = -150.0
              scaleX = .5
              scaleY = .5

          })
          add( JFXSpinner().apply {
              addClass("materialDesign-yellow"," fifth-spinner")
              startingAngle = -180.0
              scaleX = .3
              scaleY = .3

          })


      }
      tabs.apply {
          add(Tab("校园工具").apply {
              content = vbox {
                  this += Label("这里是校园工具区").apply {
                      alignment = Pos.CENTER
                      this.setOnMouseClicked {
                          if(!SystemTray.isSupported()){
                              show("当前系统不支持！",MyApp.staticStage)
                              return@setOnMouseClicked
                          }
                          SystemTray.getSystemTray().apply {
                              add(SVGIcon(color = Color.WHITE,svgShape = SVG_logo).apply {
                                  this.rotate = -180.0
                                  this.tooltip("生活助手")
                              })

                          }

                      }
                  }
                 this += sp
              }

              style{
                  fitToWidth = true
                  alignment = Pos.CENTER
              }
          })
          add(Tab("web工具").apply {
              content = Label("这里是web工具区")
              fitToParentSize()
          })
      }
  }
}