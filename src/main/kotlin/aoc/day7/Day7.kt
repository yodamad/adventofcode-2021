package aoc.day7

import aoc.shared.getLines
import javax.swing.text.html.HTML.Attribute.N
import kotlin.math.abs
import kotlin.math.sqrt


class Day7 {

    val DAY = "day7"

    fun process(fileName: String) {
        val positions = fileName.getLines(DAY)[0].split(",").map { it.toInt() }
        val opt = getOptimizedPosition(positions)
        val consumption = computeAugmentedFuel(opt.toInt(), positions)
        println(consumption)
    }

    private fun getBetterPosition(positions: List<Int>) =
        positions.sorted().filterIndexed { index, _ -> index == positions.size / 2 || index == (positions.size / 2) - 1 }
            .average().toInt()

    private fun getOptimizedPosition(positions: List<Int>) = positions.average().toInt()

    private fun computeFuel(targetPosition: Int, values: List<Int>) = values.sumOf { abs(it - targetPosition) }

    private fun computeAugmentedFuel(targetPosition: Int, values: List<Int>) =
        values.sumOf { IntRange(minOf(it, targetPosition), maxOf(it, targetPosition)).sumOf { a -> abs(a - targetPosition) } }
}