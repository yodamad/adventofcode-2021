package aoc.day9

import aoc.shared.getLines

class Day9 {

    val DAY = "day9"

    // Part 1
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

    // Part 2
    private lateinit var mapping: List<List<Int>>
    var analyzed = mutableListOf<Pair<Int, Int>>()

    fun getBasins(filename: String) {
        val positions = getPositionsOfLowPoints(filename.getLines(DAY).matrix())
        mapping = filename.getLines(DAY).matrix().toMutableList()
        var basins = mutableListOf<MutableList<Pair<Int,Int>>>()
        positions.flatten().forEach {
            basins.add(analysePoint(it.first, it.second))
        }
        //basins.sortedByDescending { it.size }.forEach { println(it) }
        val result = basins.sortedByDescending { it.size }
            .take(3).fold(1) { a, c -> a * c.size }
        println(result)
    }

    private fun getPositionsOfLowPoints(matrix: List<List<Int>>) = matrix.mapIndexed() {
            lineIdx, l -> l.mapIndexed { idx, i ->
            when (lineIdx) {
                0 -> {
                    when {
                        idx == 0 && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx+1][idx] -> lineIdx to idx
                        idx == (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx+1][idx] -> lineIdx to idx
                        idx > 0 && idx < (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx+1][idx] -> lineIdx to idx
                        else -> -1 to -1
                    }
                }
                (matrix.size - 1) -> {
                    when {
                        idx == 0 && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] -> lineIdx to idx
                        idx == (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx-1][idx]  -> lineIdx to idx
                        idx > 0 && idx < (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] -> lineIdx to idx
                        else -> -1 to -1
                    }
                }
                else -> {
                    when {
                        idx == 0 && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] && i < matrix[lineIdx+1][idx] -> lineIdx to idx
                        idx == (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx-1][idx] && i < matrix[lineIdx+1][idx] -> lineIdx to idx
                        idx > 0 && idx < (l.size - 1) && i < matrix[lineIdx][idx-1] && i < matrix[lineIdx][idx+1] && i < matrix[lineIdx-1][idx] && i < matrix[lineIdx+1][idx] -> lineIdx to idx
                        else -> -1 to -1
                    }
                }
            }
        }
    }

    private fun analysePoint(lineIdx: Int, columnIdx: Int, goingLeft: Boolean = false, goingUp: Boolean = false): MutableList<Pair<Int, Int>> {
        analyzed.add(lineIdx to columnIdx)
        val currentBasin = mutableListOf<Pair<Int, Int>>()
        if (lineIdx == -1 || columnIdx == -1 || lineIdx == mapping.size
            || columnIdx == mapping[lineIdx].size || mapping[lineIdx][columnIdx] == 9
            || currentBasin.contains(lineIdx to columnIdx)) {
            return emptyList<Pair<Int,Int>>().toMutableList()
        } else {
            currentBasin.add(lineIdx to columnIdx)
            if (!analyzed.contains(lineIdx-1 to columnIdx)) currentBasin.addAll(analysePoint(lineIdx-1, columnIdx, goingUp))
            if (!analyzed.contains(lineIdx to columnIdx-1)) currentBasin.addAll(analysePoint(lineIdx, columnIdx-1))
            if (!analyzed.contains(lineIdx to columnIdx+1)) currentBasin.addAll(analysePoint(lineIdx, columnIdx+1))
            if (!analyzed.contains(lineIdx+1 to columnIdx)) currentBasin.addAll(analysePoint(lineIdx+1, columnIdx))
            return currentBasin
        }
    }
}

fun List<String>.matrix() : List<MutableList<Int>> = this.map { it.toMutableList() }.map { s -> s.map { c -> c.toString().toInt() }.toMutableList() }