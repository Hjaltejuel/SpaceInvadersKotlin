package com.example.Data

import javafx.geometry.Rectangle2D
import javafx.scene.canvas.GraphicsContext
import javafx.scene.paint.Color

class Shot ( xInit : Double,  yInit : Double,val friendly : Boolean) : HasBoundary {

    private var x : Double = xInit + 24
    private var y: Double = yInit - 20;


    fun move(){
        if(friendly)
            y -= 8
        else
            y += 8
    }

    fun render (g : GraphicsContext){
        g.fill = Color.YELLOW
        g.fillRect(x - 2.5, y-20, 5.0, 20.0)
    }

    fun removeIfOut (shots : MutableList<Shot>){
        if(y < 0 || y > 1600) {
            shots.remove(y)
        }
    }

    override fun boundary(): Rectangle2D {
        return Rectangle2D(x - 2.5, y-20, 5.0, 20.0)
    }
}