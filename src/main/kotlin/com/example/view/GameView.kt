package com.example.view

import com.example.logic.SpaceShipController
import com.example.Styles
import javafx.scene.image.Image
import javafx.util.Duration
import tornadofx.*

class GameView : View("My View") {
    companion object{
        val sprites = Image("/sprites.png",true)
        val spaceShip = Image("/spaceship.png",true)

    }

    override val root = pane {
        setPrefSize(primaryStage.width,primaryStage.height)
        background.apply {
            addClass(Styles.backgroundcolor)
        }
        val canvas = canvas(primaryStage.width,primaryStage.height) {

            setPrefSize(primaryStage.width,primaryStage.height)
        }

        add(canvas)

        val controller  = SpaceShipController()

        fun gameOver(score : Double){
            GameOverView.score = score
            replaceWith(GameOverView(), ViewTransition.Fade(Duration(1000.0)))
        }
        controller.startGame(primaryStage, canvas) { gameOver(it) }


    }
}
