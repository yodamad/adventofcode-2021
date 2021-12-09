package aoc.day8

class Day8Test {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day8Test().testComplete()
        }
    }

    fun testDemo() {
        Day8().getEasyNb("demo.input")
    }

    fun testEasyPart() {
        Day8().getEasyNb("step1.input")
    }

    fun testCompleteDemo() {
        Day8().getAllNb("demo.input")
    }

    fun testComplete() {
        Day8().getAllNb("step1.input")
    }
}