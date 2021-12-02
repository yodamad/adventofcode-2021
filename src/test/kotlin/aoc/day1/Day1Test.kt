package aoc.day1

class Day1Test {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day1Test().testWindowing()
        }
    }

    private fun testDemo() {
        val result = Day1().computeIncreased("demo.input")
        print(result)
    }

    private fun testStep1() {
        val result = Day1().computeIncreased("step1.input")
        print(result)
    }

    private fun testWindowingDemo() {
        val result = Day1().computeWindowsIncrease("demo.input")
        print(result)
    }

    private fun testWindowing() {
        val result = Day1().computeWindowsIncrease("step1.input")
        print(result)
    }
}