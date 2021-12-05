package aoc.day5

class Day5Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day5Test().testNonDiag()
        }
    }

    fun testDemo() {
        Day5().read("demo.input")
    }

    fun testNonDiag() {
        Day5().read("step1.input")
    }
}