package com.example

import javafx.geometry.Pos
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.paint.*
import javafx.scene.text.Font
import javafx.scene.text.FontSmoothingType
import javafx.scene.text.FontWeight
import javafx.scene.text.TextAlignment
import tornadofx.*

class Styles : Stylesheet() {
    companion object {
        val heading by cssclass()
        val backgroundcolor by cssclass()
        val span by cssclass()
        val intro by cssclass()
        val center by cssclass()
        val paddingTop by cssclass()
    }

    init {
        importStylesheet("https://fonts.googleapis.com/css?family=Lato:300,400,700")
        importStylesheet("https://fonts.googleapis.com/css2?family=Cookie&display=swap")

        backgroundcolor {
            backgroundColor += RadialGradient(0.0, 0.0, 0.5,1.0 ,1.0,  true, CycleMethod.REPEAT, Stop(0.0, c("#1B2735")), Stop(1.0, c("#090A0F")))
        }

        paddingTop {
            padding = box(20.px, 0.px, 0.px, 0.px)
        }
        center {
            alignment = Pos.CENTER
        }



        button{
            backgroundColor += c("#222")
            textFill = c("#eee")
            fontSize = 40.px
            fontFamily = "Cookie"
            borderWidth += box(5.px)
            borderColor += box(Color.TRANSPARENT, Color.TRANSPARENT,Color.TRANSPARENT, Color.TRANSPARENT )
            backgroundRadius = multi(box(10.px))
            and (hover) {
                borderStyle += BorderStrokeStyle.SOLID
                backgroundColor += Color.TRANSPARENT
                borderWidth += box(5.px)
                borderColor += box(Color.TRANSPARENT, Color.TRANSPARENT,Color.BLACK, Color.TRANSPARENT )
            }
        }

        span{
            val list = listOf(Stop(0.0,Color.WHITE),Stop(1.0, c("#38495a")))
            fill = LinearGradient(1.0, 0.0, 1.0, 1.0, true, CycleMethod.NO_CYCLE, list)
            textFill = c( "#FFF" )
            textAlignment = TextAlignment.CENTER
            alignment = Pos.CENTER
            fontFamily = "lato"
            fontSize = 40.px;
            fontWeight = FontWeight.MEDIUM
            fontSmoothingType = FontSmoothingType.LCD

            padding = box(0.px,0.px,0.px,10.px)
        }

        intro {
            alignment = Pos.CENTER


        }
        label and heading {
            padding = box(10.px)
            fontSize = 20.px
            fontWeight = FontWeight.BOLD
        }
    }
}
