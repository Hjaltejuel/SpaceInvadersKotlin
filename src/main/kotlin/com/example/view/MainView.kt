package com.example.view

import com.example.Data.SpaceShipAnimation
import com.example.Styles
import com.example.logic.StarFunctions.Companion.animateStar
import com.example.logic.StarFunctions.Companion.fadeOut
import com.example.logic.StarFunctions.Companion.getStars
import javafx.animation.FadeTransition
import javafx.animation.Timeline
import javafx.animation.TranslateTransition
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration
import tornadofx.*
import kotlin.random.Random

class MainView : View("Space Invaders") {



    override val root = pane {
        background.apply {
            addClass(Styles.backgroundcolor)
        }

        setPrefSize(primaryStage.width, primaryStage.height)
        val smallStars = getStars(prefWidth.toInt(), 1.0)
        val mediumStars = getStars(prefWidth.toInt(), 2.0)

        smallStars.map { animateStar(it, 15.seconds, 15000.0) }

        mediumStars.map { animateStar(it, 25.seconds, 25000.0) }

        val allStars = smallStars + mediumStars
        var fadeOut = allStars.map { fadeOut(it, getChildList()) }

        val childList = getChildList();

        childList?.addAll(mediumStars)
        childList?.addAll(smallStars)
        var hiddenShip = SpaceShipAnimation.createSpaceShip(776.0,562.0)
        add(hiddenShip)
        val translateHidden = TranslateTransition(Duration(3000.0),hiddenShip)
        translateHidden.toY = 450.0


        hbox {
            setPrefSize(primaryStage.width,primaryStage.height)
            addClass(Styles.center)


            vbox {
                spacing = 20.0
                addClass(Styles.intro)
                val text = text("Space Invaders") {
                    addClass(Styles.span)
                }
                add(text)
                val spaceShip = SpaceShipAnimation.createSpaceShip(0.0,0.0)
                add(spaceShip)

                val b = button("Start shooting") {
                    minHeight = 50.0
                    minWidth = 150.0
                    textFill = c("#fff")
                }
                add(b)
                val childListInner = getChildList()

                b.action {
                    fadeOut.forEach{it.play()}
                    fadeOut(b,childListInner).play()
                    val tf = fadeOut(text,childList)
                    tf.setOnFinished {
                        childListInner?.remove(spaceShip)
                        translateHidden.setOnFinished {
                            val gameView = find(GameView::class)
                            replaceWith(gameView)
                            gameView.reset()
                        }
                        translateHidden.play()

                    }
                    tf.play()

                }
            }

        }
    }


}
