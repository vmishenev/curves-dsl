package jetbrains.intership

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TestNegative {

    @Test
    fun testContex1() {
        assertCompilationFails(
            """
            import jetbrains.intership.*
            
             curves {
                curve {
                    curve {  }
                    range = 1..7
                    x = t * 0
                    y = t
                }
            }
        """
        )
    }


    @Test
    fun testContex2() {
        assertCompilationFails(
                """
            import jetbrains.intership.*
            
             curves {
                curves {  }
                curve {
                    range = 1..7
                    x = t * 0
                    y = t
                }
            }
        """
        )
    }
    private fun assertCompilationFails(source: String) {
        val result = KotlinCompilation().apply {
            sources = listOf(SourceFile.kotlin("test.kt", source))
            inheritClassPath = true
        }.compile()

        Assertions.assertEquals(KotlinCompilation.ExitCode.COMPILATION_ERROR, result.exitCode)
    }
}