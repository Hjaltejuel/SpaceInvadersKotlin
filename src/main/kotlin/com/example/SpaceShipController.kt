package com.example

import com.example.Data.SpaceShip
import javafx.animation.AnimationTimer
import javafx.geometry.Point2D
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.shape.Shape
import javafx.stage.Stage
import javafx.util.Duration
import tornadofx.move

class SpaceShipController {

    fun startGame(stage : Stage,  spaceShip : Shape, x : Double, y: Double){
        val theScene = stage.scene

        val input = HashSet<String>()
        theScene.setOnKeyPressed {
            val code = it.code.toString()
            input.add(code)
        }

        theScene.setOnKeyReleased {
            val code = it.code.toString()
            input.remove(code)
        }

        var xval = x
        var leftAcceleartion : Int
        var rightAcceleration : Int
        val animationTimer = object : AnimationTimer(){
            override fun handle(now: Long) {
                if(input.contains("SPACE")){

                }
                if(input.contains("LEFT")) {
                    if(xval >= -750) {
                        spaceShip.move(Duration(5.0), Point2D(xval - 2, y))
                        xval -= 2
                    }
                }
                if(input.contains("RIGHT")) {
                    if(xval <= 750) {
                        spaceShip.move(Duration(5.0), Point2D(xval + 2, y))
                        xval += 2
                    }
                }
            }
        }
        animationTimer.start()
    }
}