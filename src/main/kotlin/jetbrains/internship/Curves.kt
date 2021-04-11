package jetbrains.internship

val STEP: Double = 1e-1
@DslMarker
@Target(AnnotationTarget.CLASS)
annotation class CurvesDsl

@CurvesDsl
class CurvesCtx {
    @Deprecated("Incorrect context", level = DeprecationLevel.ERROR)
    fun curves(curves: CurvesCtx.() -> Unit) {}

    internal fun curve(c: CurveCtx.() -> Unit) {
        val point = CurveCtx()
        val range = point.apply(c).range
        if (range != null) {
            point.t = range.start.toDouble()
            var prevX = point.x
            var prevY = point.y
            while (point.t <= range.endInclusive ) {
                point.t += STEP
                point.apply(c)
                drawLine(prevX, prevY, point.x,point.y)
                prevX = point.x
                prevY = point.y
            }
        }

    }
}

@CurvesDsl
class CurveCtx(internal var t: Double = 0.0) {
    var range: IntRange? = null
        set(v) {
            if (range == null)
                t = v?.start?.toDouble() ?: 0.0
            field = v
        }
    var x: Double = 0.0
    var y: Double = 0.0

}

fun curves(curves: CurvesCtx.() -> Unit) {
    CurvesCtx().apply(curves)
}

/*class curves {
    operator  fun   invoke(curves: CurvesCtx.() -> Unit) {
        CurvesCtx().apply(curves)
    }
}*/








