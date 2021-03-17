package com.example.Data

import javafx.scene.paint.Color
import javafx.scene.shape.Polygon
import javafx.scene.shape.Shape

class SpaceShip {
    companion object SpaceShip{
        fun createSpaceShip(x : Double, y: Double) : Shape{
            val polygon = Polygon()
            polygon.fill = Color.WHITE
            polygon.points.addAll(
                    x + 25.0, y + 0.0,
                    x + 12.5, y + 15.0,
                    x + 12.5, y + 2.0,
                    x + 10.0, y + 2.0,
                    x + 10.0, y + 16.5,
                    x + 0.0,  y + 30.0,
                    x + 15.0, y + 30.0,
                    x + 20.0, y + 25.0,
                    x + 30.0, y + 25.0,
                    x + 35.0, y + 30.0,
                    x + 50.0, y + 30.0,
                    x + 37.5, y + 15.0,
                    x + 37.5, y + 2.0,
                    x + 35.0, y + 2.0,
                    x + 35.0, y + 13.5,
            )
            return polygon
        }
    }
}