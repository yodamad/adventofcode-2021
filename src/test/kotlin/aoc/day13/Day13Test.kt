package aoc.day13

class Day13Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day13Test().testDemoFold()
            Day13Test().testFold()
        }
    }

    fun testDemoFold() {
        Day13().computeDots("demo.input")
    }

    fun testFold() {
        Day13().computeDots("step1.input")
    }
}