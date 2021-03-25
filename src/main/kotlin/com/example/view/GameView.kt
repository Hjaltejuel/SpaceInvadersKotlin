package com.example.view

import com.example.logic.SpaceShipController
import com.example.Styles
import javafx.event.EventTarget
import javafx.scene.canvas.Canvas
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.util.Duration
import tornadofx.*
import tornadofx.FX.Companion.primaryStage
import tornadofx.canvas

class GameView : View("My View") {
    companion object{
        val sprites = Image("/sprites.png",true)
        val spaceShip = Image("/spaceship.png",true)
        val controller = SpaceShipController( )
    }

    fun reset (){
        controller.reset()
        controller.startGame( )
    }

    override val root = pane {
        setPrefSize(primaryStage.width, primaryStage.height)
        background.apply {
            addClass(Styles.backgroundcolor)
        }
        val canvas = canvas(primaryStage.width, primaryStage.height) {
            setPrefSize(primaryStage.width, primaryStage.height)
        }
        add(canvas)

        canvas.graphicsContext2D.fillRect(100.0,100.0,100.0,100.0)
        fun gameOvers(score: Double) {
            GameOverView.scoreText.text =  "Score : ${score}"
            val gameOverView = find(GameOverView::class)
            gameOverView.reset()
            replaceWith(gameOverView, ViewTransition.Fade(Duration(1000.0)))
        }

        controller.init(primaryStage.width, primaryStage.height, primaryStage, canvas) { gameOvers(it) }

    }
}
