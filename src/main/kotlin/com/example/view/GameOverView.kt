package com.example.view

import com.example.Data.SpaceShipAnimation
import com.example.logic.SpaceShipController
import com.example.Styles
import com.example.logic.StarFunctions
import javafx.animation.TranslateTransition
import javafx.scene.text.Text
import javafx.util.Duration
import tornadofx.*

class GameOverView : View("My View") {

    companion object{
        var scoreText = Text("Hello")
    }

    val smallStars = StarFunctions.getStars(primaryStage.width.toInt(), 1.0)
    val mediumStars = StarFunctions.getStars(primaryStage.width.toInt(), 2.0)
    var smallStarAnimation =  smallStars.map { StarFunctions.animateStar(it, 15.seconds, 15000.0) }
    var mediumStarAnimation = mediumStars.map { StarFunctions.animateStar(it, 25.seconds, 25000.0) }


    fun reset(){


    }

    override val root = pane{
        background.apply {
            addClass(Styles.backgroundcolor)
        }

        setPrefSize(primaryStage.width, primaryStage.height)
        smallStarAnimation.forEach{it.play()}
        mediumStarAnimation.forEach{it.play()}


        val childList = getChildList();

        childList?.addAll(mediumStars)
        childList?.addAll(smallStars)


        hbox {
            setPrefSize(primaryStage.width, primaryStage.height)
            addClass(Styles.center)


            vbox {
                spacing = 20.0
                addClass(Styles.intro)
                val text = text("GAME OVER") {
                    addClass(Styles.span)
                }
                add(text)
                val spaceShip = SpaceShipAnimation.createSpaceShip(0.0, 0.0)
                add(spaceShip)
                scoreText  =   text(""){
                    addClass(Styles.score)
                }
                add(scoreText)
                val b = button("Retry?") {
                    minHeight = 50.0
                    minWidth = 150.0
                    textFill = c("#fff")
                }
                add(b)
                val childListInner = getChildList()

                b.action {
                    childListInner?.remove(spaceShip)
                    val gameView = find(GameView::class)
                    gameView.reset()
                    replaceWith(gameView, ViewTransition.Fade(Duration(1000.0)))
                }
            }

        }
    }
}