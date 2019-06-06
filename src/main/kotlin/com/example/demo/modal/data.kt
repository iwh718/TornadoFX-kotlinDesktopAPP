package com.example.demo.modal

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*
import javax.json.JsonObject

/**
 * 图书Modal,JsonModal可以很方便进行Modal与JSON转换，由于接口格式有点问题，这里没使用。
 */
class Books(name: String, number: String) : JsonModel {
    private val numberProperty = SimpleStringProperty(number)
    var number by numberProperty
    private val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

}