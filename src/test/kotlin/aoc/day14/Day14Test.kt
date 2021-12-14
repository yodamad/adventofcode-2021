package aoc.day14

class Day14Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day14Test().testLongPolymer()
        }
    }

    fun testDemo() {
        Day14().makePolymer("demo.input", 10)
    }

    fun testPolymer() {
        Day14().makePolymer("sequence.input", 10)
    }

    fun testLongPolymer() {
        Day14().makePolymer("sequence.input", 40)
    }
}