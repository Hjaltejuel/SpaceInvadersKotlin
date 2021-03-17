package com.example.Data

import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import tornadofx.CssRule.Companion.c

class Star(private val acceleration : Int, private val xCord : Double,var yCord : Double, private val size : Double) {

     fun toCircle() : Circle {
         yCord += 2 * acceleration
         return Circle(xCord, yCord, size, Color.WHITE )
     }
}