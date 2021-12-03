package aoc.day3

class Day3Test {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            Day3Test().testOxygen()
        }
    }

    fun testDemo() {
        Day3().computePowerConsumption("demo.input")
    }

    fun testPowerConsumption() {
        Day3().computePowerConsumption("step1.input")
    }

    fun testDemoOxygen() {
        Day3().computeOxygen("demo.input")
    }

    fun testOxygen() {
        Day3().computeOxygen("step1.input")
    }
}