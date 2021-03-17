package com.example.view

import com.example.Data.SpaceShip
import com.example.Data.Star
import com.example.SpaceShipController
import com.example.Styles
import javafx.animation.Animation
import javafx.animation.FadeTransition
import javafx.animation.Timeline
import javafx.animation.TranslateTransition
import javafx.geometry.Insets
import javafx.scene.Node
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.util.Duration
import tornadofx.*
import kotlin.random.Random

class MainView : View("Space Invaders") {
    override val root = pane {
        val p = this
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
        var hiddenShip = SpaceShip.createSpaceShip(776.0,562.0)
        add(hiddenShip)
        val translateHidden = TranslateTransition(Duration(3000.0),hiddenShip)
        translateHidden.toY = 450.0

        val controller  = SpaceShipController()
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
                val spaceShip = SpaceShip.createSpaceShip(0.0,0.0)
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
                            controller.startGame(primaryStage,  hiddenShip, 0.0, 450.0 )
                        }
                        translateHidden.play()

                    }
                    tf.play()

                }
            }

        }
    }

    private fun getStars(width : Int, size : Double) : List<Circle> {
        tailrec fun recurse (from : Int, acc : List<Circle> ) : List<Circle> {
            return when(from){
                0 -> acc
                else ->
                    if(Random.nextInt() % 10 == 1) {

                        recurse(from - 1, acc + Circle(from.toDouble(), 1200.0, size, Color.WHITE))
                    }
                    else
                        recurse(from -1, acc)
            }
        }
        return recurse(width, emptyList())

    }

    private fun animateStar(it : Circle, duration: Duration, end : Double) {
        timeline {
            keyframe(duration){
                keyvalue(it.translateYProperty(), -1200)
            }
            delay = Duration(Random.nextDouble(end))
            cycleCount = Timeline.INDEFINITE
        }
    }

    private fun fadeOut(x : Node, childList : MutableList<Node>?) : FadeTransition{
        val fadeTransition = FadeTransition(Duration(2000.0),x)
        fadeTransition.toValue = 0.0
        fadeTransition.setOnFinished {
            childList?.remove(x)
        }
        return fadeTransition
    }

}
