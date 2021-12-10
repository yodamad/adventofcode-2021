package aoc.day9

import aoc.shared.getLines

class Day9 {

    val DAY = "day9"

    fun getLowPositions(filename: String): Int {
        val matrix = filename.getLines(DAY).matrix()
        val sumOfLowPoints = getMapWithLowPoints(matrix).flatten().sum()
        println(sumOfLowPoints)
        return sumOfLowPoints
    }

    private fun getMapWithLowPoints(matrix: List<List<Int>>) = matrix.mapIndexed() {
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
        }

    fun getBasins(filename: String): List<List<Int>> {
        val matrix = filename.getLines(DAY).matrix().toMutableList()
        val result = matrix.mapIndexed { lineIdx, l ->
            computeLine(matrix, lineIdx, l)
        }
        return result
    }

    private fun computeLine(matrix: List<MutableList<Int>>, lineIdx: Int, l: List<Int>): List<Int> {
        val r = l.mapIndexed { idx, i ->
            var basinSize = 0
            if (i != 9 && i != -1) {
                basinSize++
                matrix[lineIdx][idx] = -1

                for (r in lineIdx until matrix.size) {
                    var newIdx = idx+1
                    while (newIdx < l.size && matrix[lineIdx][newIdx] != 9) {
                        matrix[lineIdx][newIdx] = -1
                        newIdx++
                        basinSize++
                    }
                }
            }
            // Set basinSize
            //println(basinSize)
            basinSize
        }
        println(r)
        return r
    }
}

fun List<String>.matrix() : List<MutableList<Int>> = this.map { it.toMutableList() }.map { s -> s.map { c -> c.toString().toInt() }.toMutableList() }