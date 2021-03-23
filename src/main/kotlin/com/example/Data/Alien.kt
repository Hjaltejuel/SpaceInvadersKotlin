package com.example.Data

import com.example.view.GameView
import javafx.geometry.Rectangle2D
import javafx.scene.canvas.GraphicsContext
import kotlin.random.Random

class Alien (private val column: Int, private val row : Int, private val type : AlienType, private val enemyShots : MutableList<Shot>) : HasBoundary {

    var x : Double = 50 + (column * 100.0) + 100
    var y : Double = (row*100.0)
    var state = false
    var toTheRight = true
    var isFiring = false
    var isDead = false
    var deathTime : Long = 0L
    var board : Array<Array<Alien>>? = null


    fun reset(){
        x  = 50 + (column * 100.0) + 100
        y  = (row*100.0)
        state = false
        toTheRight = true
        isFiring = board!![column].size == row + 1
        isDead = false
        deathTime = 0L
    }

    fun init(board : Array<Array<Alien>>) {
        this.board = board
        isFiring = board[column].size == row + 1
    }

    fun moveToSide (){
        state = !state
        if(toTheRight)
            x += 10
        else
            x -= 10
    }

    fun moveRow(){
        y+= 25
    }

    private fun createShot() : Shot{
        return Shot(x, y + 100, false)
    }


    private fun setFiring() {
        if(!isDead){
            isFiring = true
        }else if(row != 0){
            board!![column][row-1].setFiring()
        }
    }

    override fun  boundary() : Rectangle2D {
        return Rectangle2D(x, y, 50.0, 40.0)
    }

    fun destroy(deathTime : Long){
        isDead = true
        this.deathTime = deathTime
        if(isFiring && row != 0){
            board!![column][row-1].setFiring()
        }


    }

    fun intersects(shape : HasBoundary) : Boolean {
        if(isDead)
            return false
        return shape.boundary().intersects(boundary())
    }


    fun render(g : GraphicsContext, now : Long, firingRate : Double){
        if(isDead && (now - deathTime) / 1000000000.0 < 1)
            g.drawImage(GameView.sprites, 345.0, 600.0, 120.0, 120.0, x, y,  50.0 ,  50.0)
        else if(!isDead) {
            type.drawSprite(state, g, x, y)

            if (isFiring && Random.nextInt(firingRate.toInt()) < 10)
                enemyShots.add(createShot())
        }
    }


}