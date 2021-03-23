package com.example.logic

import javafx.animation.FadeTransition
import javafx.animation.Timeline
import javafx.scene.Node
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.util.Duration
import tornadofx.keyframe
import tornadofx.timeline
import kotlin.random.Random

class StarFunctions {
    companion object{
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

        fun animateStar(it : Circle, duration: Duration, end : Double) {
            timeline {
                keyframe(duration){
                    keyvalue(it.translateYProperty(), -1200)
                }
                delay = Duration(Random.nextDouble(end))
                cycleCount = Timeline.INDEFINITE
            }
        }

        fun fadeOut(x : Node, childList : MutableList<Node>?) : FadeTransition {
            val fadeTransition = FadeTransition(Duration(2000.0),x)
            fadeTransition.toValue = 0.0
            fadeTransition.setOnFinished {
                childList?.remove(x)
            }
            return fadeTransition
        }
    }
}