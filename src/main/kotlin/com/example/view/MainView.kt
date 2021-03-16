package com.example.view

import com.example.Styles
import javafx.animation.Animation
import javafx.animation.Timeline
import javafx.geometry.Insets
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
        background.apply {
            addClass(Styles.backgroundcolor)
        }
        setPrefSize(primaryStage.width, primaryStage.height)
        val smallStars = getStars(prefWidth.toInt(), 1.0)
        val mediumStars = getStars(prefWidth.toInt(), 2.0)

        smallStars.map {
           timeline {
                keyframe(15.seconds){
                    keyvalue(it.translateYProperty(), -1200)
                }
                delay = Duration(Random.nextDouble(15000.0))
                cycleCount = Timeline.INDEFINITE
            }

        }

        mediumStars.map {
            timeline {
                keyframe(25.seconds){
                    keyvalue(it.translateYProperty(), -1200)
                }
                delay = Duration(Random.nextDouble(25000.0))
                cycleCount = Timeline.INDEFINITE
            }

        }



        getChildList()?.addAll(mediumStars)
        getChildList()?.addAll(smallStars)
        hbox {
            setPrefSize(primaryStage.width,primaryStage.height)

            addClass(Styles.center)


            vbox {
                spacing = 20.0
                addClass(Styles.intro)
                text("Space Invaders") {
                    addClass(Styles.span)
                }

                button("Start shooting") {
                    minHeight = 50.0
                    minWidth = 150.0
                    textFill = c("#fff")
                }


            }

        }
    }

    fun getStars(width : Int, size : Double) : List<Circle> {
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

}
