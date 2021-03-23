package com.example.view

import com.example.Data.SpaceShipAnimation
import com.example.logic.SpaceShipController
import com.example.Styles
import com.example.logic.StarFunctions
import javafx.animation.TranslateTransition
import javafx.util.Duration
import tornadofx.*

class GameOverView : View("My View") {

    companion object{
        var score : Double = 0.0
    }
    override val root = pane{

        background.apply {
            addClass(Styles.backgroundcolor)
        }

        setPrefSize(primaryStage.width, primaryStage.height)
        val smallStars = StarFunctions.getStars(prefWidth.toInt(), 1.0)
        val mediumStars = StarFunctions.getStars(prefWidth.toInt(), 2.0)

        smallStars.map { StarFunctions.animateStar(it, 15.seconds, 15000.0) }

        mediumStars.map { StarFunctions.animateStar(it, 25.seconds, 25000.0) }

        val allStars = smallStars + mediumStars
        var fadeOut = allStars.map { StarFunctions.fadeOut(it, getChildList()) }

        val childList = getChildList();

        childList?.addAll(mediumStars)
        childList?.addAll(smallStars)
        var hiddenShip = SpaceShipAnimation.createSpaceShip(776.0, 550.0)

        val translateHidden = TranslateTransition(Duration(3000.0), hiddenShip)
        translateHidden.toY = 450.0
        fun addHiden(){
            add(hiddenShip)
        }

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
                val score =   text("Score : $score"){
                    addClass(Styles.score)
                }
                add(score)
                val b = button("Retry?") {
                    minHeight = 50.0
                    minWidth = 150.0
                    textFill = c("#fff")
                }
                add(b)
                val childListInner = getChildList()

                b.action {
                    fadeOut.forEach { it.play() }
                    StarFunctions.fadeOut(score, childListInner).play()
                    StarFunctions.fadeOut(b, childListInner).play()

                    val tf = StarFunctions.fadeOut(text, childList)
                    tf.setOnFinished {
                        addHiden()
                        childListInner?.remove(spaceShip)
                        translateHidden.setOnFinished {
                            replaceWith(GameView())
                        }
                        translateHidden.play()

                    }
                    tf.play()

                }
            }

        }
    }
}