package com.example.logic

import com.example.Data.Alien
import com.example.Data.AlienType
import com.example.Data.Shot
import com.example.Data.SpaceShip
import com.example.view.GameOverView
import com.example.view.GameView
import javafx.animation.AnimationTimer
import javafx.geometry.VPos
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Pane
import javafx.scene.text.TextAlignment
import javafx.stage.Stage
import tornadofx.replaceWith


class SpaceShipController {

    fun startGame(stage: Stage, canvas: Canvas, gameOver : (Double) -> Unit){
        val theScene = stage.scene
        val graphics = canvas.graphicsContext2D
        val input = HashSet<String>()
        theScene.setOnKeyPressed {
            val code = it.code.toString()
            input.add(code)
        }



        theScene.setOnKeyReleased {
            val code = it.code.toString()
            input.remove(code)
        }


        val spaceShip  = SpaceShip(stage.width , stage.height, stage )
        var lastTime = 0L
        var alienTypes = arrayOf(AlienType.ALIEN1TYPE, AlienType.ALIEN2TYPE, AlienType.ALIEN3TYPE)
        var shots = mutableListOf<Shot>()


        val board : Array<Array<Alien>>
        board = Array(14){i -> Array(5){k -> Alien(i,k,alienTypes[k%3],shots) }}

        board.forEach { it.forEach { alien -> alien.init(board) } }

        var neededTime = 0L
        var numberOfDeath = 0
        var neededMoveTime = 1.5
        var firingRate = 20000.0
        var score = 0.0

        fun decreaseNeededTime(numberOfDeath : Int){
            if(numberOfDeath%10 == 0)
            {
                neededMoveTime /= 1.2
                firingRate /= 1.2
            }
        }

        val animationTimer = object : AnimationTimer(){
            override fun handle(now: Long) {
                val elapsed = (now - lastTime) / 1000000000.0;
                var elapsedNeeded = (now - neededTime) / 1000000000.0;

                graphics.clearRect(0.0,0.0,stage.width,stage.height)
                spaceShip.move(input)
                graphics.setTextAlign(TextAlignment.CENTER);
                graphics.setTextBaseline(VPos.CENTER);
                graphics.fill = javafx.scene.paint.Color.WHITE
                graphics.fillText("SCORE : $score", 100.0, 100.0)
                if(elapsed > 2 && input.contains("SPACE")){
                    shots.add(spaceShip.createShot())
                    lastTime = now
                }


                if(elapsedNeeded > neededMoveTime){
                    var moveRow = false
                    board.forEach { it.forEach {
                        if(it.x < 100 && !it.toTheRight){
                            moveRow = true
                        } else if( it.x > stage.width - 150 && it.toTheRight){
                            moveRow = true
                        }

                    } }
                    if(moveRow)
                        board.forEach { it.forEach {
                            it.moveRow()
                            it.toTheRight = !it.toTheRight
                        } }
                    else  board.forEach { it.forEach {
                        it.moveToSide()
                    } }
                    neededTime = now
                }

                board.forEach { it.forEach { alien ->
                    val shotIterator = shots.iterator()
                    while (shotIterator.hasNext()) {
                        val shot = shotIterator.next()
                        if (alien.intersects(shot)) {
                            alien.destroy(now)
                            shotIterator.remove()
                            numberOfDeath++
                            decreaseNeededTime(numberOfDeath)
                            score += 10
                        }
                        if(spaceShip.intersects(shot)) {
                            this.stop()
                            gameOver(score)
                        }
                    }
                    alien.render(graphics, now, firingRate )
                } }

                spaceShip.render(graphics)
                shots.forEach{
                    it.move()
                    it.render(graphics)
                    it.removeIfOut(shots)
                }

            }
        }
        animationTimer.start()


    }
}