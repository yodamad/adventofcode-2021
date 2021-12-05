package aoc.day5

import aoc.shared.getLines

class Day5 {

    val DAY = "day5"
    val regex = "\\D+".toRegex()

    fun read(filename: String) {
        val vectors = filename.getLines(DAY)
            .map { computeVector(it) }.toMutableList()

        val t = vectors//.filter { it.isNotDiag() }
            .map { it.allPoints() }
            .flatten()
            .groupBy { e -> e }
            .map { m -> m.value.size }
            .count { v -> v > 1 }

        println(t)
    }

    fun computeVector(line: String): Vector {
        val w = line.split(regex)
        return Vector(w[0].toInt(), w[1].toInt(), w[2].toInt(), w[3].toInt())
    }
}

data class Vector(val x1: Int, val y1: Int, val x2: Int, val y2: Int) {
    fun isNotDiag() = x1 == x2 || y1 == y2

    fun allPoints(): MutableList<Pair<Int, Int>> {
        val points = mutableListOf<Pair<Int, Int>>()
        if (x1 == x2) {
            computePoint(y1, y2).forEach { points.add(Pair(x1, it)) }
        } else if (y1 == y2) {
            computePoint(x1, x2).forEach { points.add(Pair(it, y1)) }
        } else {
            computePoint(x1, x2).forEachIndexed { idx, i -> points.add(Pair(minOf(x1, x2) + idx, computeY(idx))) }
            //println(points)
        }
        return points
    }

    private fun computeY(idx: Int): Int {
        val x = minOf(x1, x2)
        return if (x == x1) {
            if (y1 < y2) {
               y1 + idx
            } else {
                y1 - idx
            }
        } else {
            if (y1 < y2) {
                y2 - idx
            } else {
                y2 + idx
            }
        }
    }
    private fun computePoint(index1: Int, index2: Int) = IntRange(minOf(index1, index2), maxOf(index1, index2))
}