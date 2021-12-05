package aoc.day4

import aoc.shared.getLines

class Day4 {

    val DAY = "day4"

    fun read(filename: String, getWinner: Boolean = true) {
        val lines = filename.getLines(DAY)
        val draw = lines[0].split(",").map { it.toInt() }
        val bingos = lines.drop(2).filter { it.isNotEmpty() }.chunked(5).map { buildBingo(it as MutableList<String>) }.toMutableList()

        var result: Int = 0
        run lit@{
            draw.forEach { d ->
                bingos.map { b -> b.mark(d) }
                //println("draw = $d")
                var score: Int = 0
                if (getWinner) {
                    score = bingos.firstOrNull { it.findWinner() }?.computeScore()!!
                } else {
                    var removedBingo: Bingo? = null
                    bingos.filter { it.findWinner() }.map {
                        removedBingo = it
                        bingos.remove(it)
                    }
                    if (bingos.size == 0) score = removedBingo?.computeScore()!!
                }
                if (score > 0) {
                    result = d * score
                    return@lit
                }
            }
        }
        println("r = $result")
    }

    private fun buildBingo(rows: MutableList<String>): Bingo {
        val bingo = Bingo()
        rows.map {
                val row = it.split(" ").filter { s -> s.isNotEmpty() }.map { s -> s.toInt() }
                bingo.rows.add(row as MutableList<Int>)
                row.forEachIndexed { index, i -> bingo.columns[index].add(i) }
            }
        return bingo
    }
}

private class Bingo(var rows: MutableList<MutableList<Int>> = mutableListOf(), var columns: MutableList<MutableList<Int>> = mutableListOf()) {
    init {
        repeat(5) {
            columns.add(mutableListOf())
        }
    }

    override fun toString(): String {
        return this.rows.toString() + this.columns.toString()
    }

    fun mark(draw: Int) {
        rows.map { it.remove(draw) }
        columns.map { it.remove(draw) }
    }

    fun findWinner() = rows.any { it.isEmpty() } || columns.any { it.isEmpty() }

    fun computeScore() : Int {
        val all = mutableListOf<Int>()
        all += rows.flatten()
        all += columns.flatten()
        return all.distinct().sum()
    }
}
