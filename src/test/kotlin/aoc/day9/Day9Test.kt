package aoc.day9

class Day9Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val r = Day9Test().testDemoBasins()
            println(r)
        }
    }

    fun testDemo() {
        Day9().getLowPositions("demo.input")
    }

    fun testStep1() {
        Day9().getLowPositions("step1.input")
    }

    fun testDemoBasins() {
        Day9().getBasins("demo.input")
    }
}