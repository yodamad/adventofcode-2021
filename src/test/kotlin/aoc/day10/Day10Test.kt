package aoc.day10

class Day10Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day10Test().testComplete()
        }
    }

    fun testDemo() { Day10().findCorrupted("demo.input") }

    fun testComplete() { Day10().findCorrupted("step1.input") }
}