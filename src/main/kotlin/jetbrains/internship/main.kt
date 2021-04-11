package jetbrains.internship

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.stage.Stage


val HEIGHT: Double = 50.0
val SCALE: Double = 50.0
val pane: Group = Group()

fun drawLine(x0: Double, y0: Double, x1: Double, y1: Double) {
    val line = Line()
    line.setStroke(Color.RED);
    line.setStrokeWidth(1.0);
    line.setStartX(x0*SCALE)
    line.setStartY(y0*SCALE)
    line.setEndX(x1*SCALE)
    line.setEndY(y1*SCALE)
    pane.getChildren().add(line);
}

class Main : Application() {

    override fun start(primaryStage: Stage) {
        val scene = Scene(pane, 200.0, 400.0)
        primaryStage.setScene(scene)

        primaryStage.show()

        Test.main()
    }


    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            launch(Main::class.java)
        }


    }
}
