package aoc.day9

class Day9Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day9Test().testStep1()
        }
    }

    fun testDemo() {
        Day9().getLowPositions("demo.input")
    }

    fun testStep1() {
        Day9().getLowPositions("step1.input")
    }
}