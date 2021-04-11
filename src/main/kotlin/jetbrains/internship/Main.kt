package jetbrains.internship

import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Line
import javafx.stage.Stage

val WIDTH_WINDOWS: Double = 200.0
val HEIGHT_WINDOWS: Double = 400.0
val STROKE: Double = 2.0
val SCALE: Double = 50.0
val group: Group = Group()

fun drawLine(x0: Double, y0: Double, x1: Double, y1: Double) {
    val line = Line()
    line.setStroke(Color.RED)
    line.setStrokeWidth(STROKE)
    line.setStartX(x0 * SCALE)
    line.setStartY(HEIGHT_WINDOWS - y0 * SCALE)
    line.setEndX(x1 * SCALE)
    line.setEndY(HEIGHT_WINDOWS - y1 * SCALE)
    group.getChildren().add(line)
}

/*
For draw curves in JavaFX window
 */
class Main : Application() {
    override fun start(primaryStage: Stage) {
        val scene = Scene(group, WIDTH_WINDOWS, HEIGHT_WINDOWS)
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

class Test {
    companion object {
        /*
        For testing of sample
         */
        @JvmStatic
        fun main(args: Array<String>? = null) {
            curves {
                curve {
                    range = 0..7
                    x = t * 0
                    y = t
                }
                curve {
                    range = -1..1
                    x = 3 * (1 - t * t)
                    y = 4 + (t + 1) * 3 / 2
                }

                curve {
                    range = -2..2
                    x = 4 - t * t
                    y = t + 2
                }
            }

        }
    }
}
