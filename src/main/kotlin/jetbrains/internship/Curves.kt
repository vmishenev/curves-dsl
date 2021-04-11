package jetbrains.internship

val SIZE = 10
val mtr = Array(SIZE) { BooleanArray(SIZE) }

@DslMarker
@Target(AnnotationTarget.CLASS)
annotation class CurvesDsl


@CurvesDsl
class CurvesCtx {
    internal fun curve(c: CurveCtx.() -> Unit) {
        var point = CurveCtx()
        var range = point.apply(c).range
        println(range)
        if (range != null) {
            point.t = range.start.toDouble()
            var prevX = point.x
            var prevY = point.y
            while (point.t <= range.endInclusive ) {
                point.t +=0.1
                point.apply(c)
                drawLine(prevX, prevY, point.x,point.y)
                prevX = point.x
                prevY = point.y
                print("(${point.x}, ${point.y})")
               // mtr[point.x][point.y] = true;
            }
        }

    }
}

@CurvesDsl
class CurveCtx(var t: Double = 0.0) {
    var range: IntRange? = null
        set(v) {
            if (range == null)
                t = v?.start?.toDouble() ?: 0.0
            field = v
        }
    var x: Double = 0.0
    var y: Double = 0.0

}

/*class curves {
    operator  fun   invoke(curves: CurvesCtx.() -> Unit) {
        CurvesCtx().apply(curves)
    }
}*/
public fun curves(curves: CurvesCtx.() -> Unit) {
    CurvesCtx().apply(curves)
}



class Test {
    companion object {
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
            println()
            for (i in 0 until SIZE) {
                for (j in 0 until SIZE) {
                    print(if (mtr[i][j]) "X" else " ")
                }
                println()
            }
        }
    }
}




