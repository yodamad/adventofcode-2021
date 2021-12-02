package aoc.day2

class Day2Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day2Test().testAdvanced()
        }
    }

    private fun testDemo() {
        val result = Day2().move("demo.input")
        print(result)
    }

    private fun testMove() {
        val result = Day2().move("step1.input")
        print(result)
    }

    private fun testAdvancedDemo() {
        val result = Day2().advancedMove("demo.input")
        print(result)
    }

    private fun testAdvanced() {
        val result = Day2().advancedMove("step1.input")
        print(result)
    }
}