package com.example.demo.modal

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.*
import javax.json.JsonObject

/**
 * 图书Modal
 */
class Books(name: String, number: String) : JsonModel {
    private val numberProperty = SimpleStringProperty(number)
    var number by numberProperty
    private val nameProperty = SimpleStringProperty(name)
    var name by nameProperty

}