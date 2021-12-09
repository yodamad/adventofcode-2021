package aoc.day9

import aoc.shared.getLines

class Day9 {

    val DAY = "day9"

    fun getLowPositions(filename: String) {
        val matrix = filename.getLines(DAY).matrix()

        val map = matrix.mapIndexed() {
            lineIdx, l -> l.mapIndexed { idx, i ->
            when (lineIdx) {
                0 -> {
                    when {
                        idx == 0 && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx+1][idx] -> i + 1
                        idx == (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx+1][idx] -> i + 1
                        idx > 0 && idx < (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx+1][idx] -> i + 1
                        else -> 0
                    }
                }
                (matrix.size - 1) -> {
                    when {
                        idx == 0 && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] -> i + 1
                        idx == (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx-1][idx]  -> i + 1
                        idx > 0 && idx < (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] -> i + 1
                        else -> 0
                    }
                }
                else -> {
                    when {
                        idx == 0 && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] && i < matrix[lineIdx+1][idx] -> i + 1
                        idx == (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx-1][idx] && i < matrix[lineIdx+1][idx] -> i + 1
                        idx > 0 && idx < (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] && i < matrix[lineIdx+1][idx] -> i + 1
                        else -> 0
                    }
                }
            }
            }
        }.flatten().sum()

        println(map)
    }
}

fun List<String>.matrix() : List<List<Int>> = this.map { it.toMutableList() }.map { s -> s.map { c -> c.toString().toInt() } }