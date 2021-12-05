package aoc.day4

class Day4Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day4Test().testLosingBingo()
        }
    }

    fun testDemo() {
        Day4().read("demo.input")
    }

    fun testBingo() {
        Day4().read("step1.input")
    }

    fun testDemoLoser() {
        Day4().read("demo.input", false)
    }

    fun testLosingBingo() {
        Day4().read("step1.input", false)
    }
}