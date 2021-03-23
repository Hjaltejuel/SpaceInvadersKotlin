package com.example.Data;

import javafx.geometry.Rectangle2D
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color
import javafx.stage.Stage

class SpaceShip (val screenWidth : Double, val screenHeight : Double) : HasBoundary {


    private var xVelocity : Double = 0.0
    private var x : Double = screenWidth/2 - 24
    private var y : Double = screenHeight - 188

    fun reset(){
        xVelocity  = 0.0
        x  = screenWidth/2 - 24
        y  = screenHeight - 188
    }

    fun move (input : HashSet<String>){
        when{
            input.contains("LEFT") -> {
                if(x >= 100) {
                    xVelocity -= 0.2
                    updateX(xVelocity)
                }
            }
            input.contains("RIGHT") -> {
                if(x <= screenWidth - 100) {
                    xVelocity += 0.2
                    updateX(xVelocity)
                }
            }
            else -> {
                if( xVelocity > 0)
                    xVelocity -= 0.2
                else if (xVelocity < 0 )
                    xVelocity += 0.2

                if(Math.abs(xVelocity) <= 0.2)
                    xVelocity = 0.0;
                updateX(xVelocity)
            }
        }

    }

    fun createShot() : Shot{
        return Shot(x, y, true)
    }

    private fun updateX(velocity : Double){
        if(x + velocity < 100) {
            x = 100.0;
            xVelocity = -xVelocity
        }
        else if(x + velocity >screenWidth - 100) {
            x = screenWidth- 100
            xVelocity = -xVelocity
        }
        else
            x += velocity
    }

    override fun  boundary() : Rectangle2D {
        return Rectangle2D(x, y, 50.0, 30.0)
    }


    fun intersects(shape : HasBoundary) : Boolean {
        return shape.boundary().intersects(boundary())
    }

    fun render(g : GraphicsContext){
        g.fill = Color.WHITE

        var xPoints  = doubleArrayOf(
                x + 25.0,
                x + 12.5,
                x + 12.5,
                x + 10.0,
                x + 10.0,
                x + 0.0,
                x + 15.0,
                x + 20.0,
                x + 30.0,
                x + 35.0,
                x + 50.0,
                x + 37.5,
                x + 37.5,
                x + 35.0,
                x + 35.0)
        var yPoints = doubleArrayOf(
                y + 0.0,
                y + 15.0,
                y + 2.0,
                y + 2.0,
                y + 16.5,
                y + 30.0,
                y + 30.0,
                y + 25.0,
                y + 25.0,
                y + 30.0,
                y + 30.0,
                y + 15.0,
                y + 2.0,
                y + 2.0,
                y + 13.5)


        return g.fillPolygon(xPoints, yPoints, 15)
    }

}
