package aoc.day3

import aoc.shared.getLines

class Day3 {

    val DAY = "day3"

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val t = mapOf<Int, List<String>>(1 to listOf("111", "000"), 2 to listOf("101", "010"), 3 to listOf("110", "001"))
            println(t.toList().sortedByDescending { it.second[1][2] }.toMap())
        }
    }

    fun computePowerConsumption(filename: String) {
        val digits = mutableListOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        val size = filename.getLines(DAY).size

        filename.getLines(DAY).map {
            it.forEachIndexed { index, c ->
                digits[index] += c.toString().toInt()
            }
        }

        val gamma = digits.map {
            if (it.toString().toInt() > size/2) {
                1
            } else { 0 }
        }

        val epsilon = gamma.map {
            (it + 1) % 2
        }

        println(gamma.joinToString("").toLong(2) * epsilon.joinToString("").toLong(2))
    }

    fun computeOxygen(filename: String) {

        var values = filterMax(filename.getLines(DAY), 0)
        val maxIndex = values!!.value.size
        var index = 1

        while (values!!.value.size > 1 && index <= maxIndex) {
            values = filterMax(values.value, index)
            index++
        }

        val oxygen = values.value[0].toLong(2)
        println("oxygen is $oxygen")

        values = filterMin(filename.getLines(DAY), 0)
        index = 1
        while (values!!.value.size > 1 && index <= maxIndex) {
            values = filterMin(values.value, index)
            index++
        }

        val co2 = values.value[0].toLong(2)
        println("co2 is $co2")

        println(oxygen.toInt() * co2.toInt())
    }

    private fun filterMax(elements: List<String>, round: Int) =
        elements.sortedDescending()
            .groupBy { it[round].toString().toInt() }
            .toList().sortedByDescending { it.second[0][round] }
            .toMap()
            .maxByOrNull { it.value.size }

    private fun filterMin(elements: List<String>, round: Int) =
        elements
            .groupBy { it[round].toString().toInt() }
            .toList().sortedBy { it.second[0][round] }
            .toMap()
            .minByOrNull { it.value.size }
}