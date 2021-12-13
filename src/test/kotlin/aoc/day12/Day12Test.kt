package aoc.day12

class Day12Test {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day12Test().testLightDemo()
        }
    }

    fun testLightDemo() {
        Day12().getPaths("light.input")
    }
}