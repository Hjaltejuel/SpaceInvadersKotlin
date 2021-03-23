package com.example

import com.example.view.MainView
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.stage.Stage
import tornadofx.App
import tornadofx.add
import tornadofx.importStylesheet
import kotlin.random.Random

class MyApp: App(MainView::class, Styles::class){
    override fun start(stage: Stage) {
        super.start( stage.apply {
            width = 1600.0
            height = 1200.0
            stage.isResizable = false
        })


    }

}