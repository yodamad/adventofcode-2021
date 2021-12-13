package aoc.day9

class Day9Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day9Test().testBasins()
            //Day9Test().testDemoBasins()
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

    fun testBasins() {
        Day9().getBasins("step1.input")
    }
}