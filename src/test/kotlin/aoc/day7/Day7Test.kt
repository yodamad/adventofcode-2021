package aoc.day7

class Day7Test {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day7Test().testDemo()
            Day7Test().testStep1()
        }
    }

    fun testDemo() {
        Day7().process("demo.input")
    }

    fun testStep1() {
        Day7().process("step1.input")
    }
}