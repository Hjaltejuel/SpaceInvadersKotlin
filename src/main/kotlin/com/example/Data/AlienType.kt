package com.example.Data

import com.example.view.GameView
import javafx.scene.canvas.GraphicsContext

enum class AlienType {
    ALIEN1TYPE{
        override fun drawSprite(state : Boolean, g : GraphicsContext, x : Double, y: Double){
            if(state)
                g.drawImage(GameView.sprites, 0.0, 0.0, 150.0, 120.0, x, y, 50.0 , 40.0)
            else
                g.drawImage(GameView.sprites, 150.0, 0.0, 150.0, 120.0, x, y, 50.0 , 40.0)
        }
    },
    ALIEN2TYPE{
        override fun drawSprite(state : Boolean, g : GraphicsContext, x : Double, y: Double){
            if(state)
                g.drawImage(GameView.sprites, 0.0, 120.0, 150.0, 120.0, x, y, 50.0 , 40.0)
            else
                g.drawImage(GameView.sprites, 150.0, 120.0, 150.0, 120.0, x, y, 50.0 , 40.0)
        }
    },
    ALIEN3TYPE{
        override fun drawSprite(state : Boolean, g : GraphicsContext, x : Double, y: Double){
            if(state)
                g.drawImage(GameView.sprites, 0.0, 240.0, 100.0, 120.0, x, y, 50.0 , 40.0)
            else
                g.drawImage(GameView.sprites, 100.0, 240.0, 100.0, 120.0, x, y, 50.0 , 40.0)
        }
    };

    abstract fun drawSprite(state : Boolean, g : GraphicsContext, x : Double, y: Double)
}