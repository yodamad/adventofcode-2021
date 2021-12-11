package aoc.day11

class Day11Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day11Test().testSimultaneous()
        }
    }

    fun testVeryDemo() {
        Day11().flashOctopuses("demo.input", 2)
    }

    fun testVerySimple() {
        Day11().flashOctopuses("simple.input", 100)
    }

    fun testVeryComplete() {
        Day11().flashOctopuses("complete.input", 100)
    }

    fun testSimpleSimultaneous() {
        Day11().flashSimultaneous("simple.input")
    }

    fun testSimultaneous() {
        Day11().flashSimultaneous("complete.input")
    }
}