package aoc.day8

import aoc.shared.getLines

class Day8 {

    val DAY = "day8"

    val digits = mutableMapOf<Int, List<Int>>()
    init {
        digits[1] = listOf(2, 5)
        digits[7] = listOf(0, 2, 5)
        digits[4] = listOf(1, 2, 3, 5)
        digits[2] = listOf(0, 2, 3, 4, 6)
        digits[3] = listOf(0, 2, 3, 5, 6)
        digits[5] = listOf(0, 1, 3, 5, 6)
        digits[0] = listOf(0, 1, 2, 4, 5, 6)
        digits[6] = listOf(0, 1, 3, 4, 5, 6)
        digits[9] = listOf(0, 1, 2, 3, 5, 6)
        digits[8] = listOf(0, 1, 2, 3, 4, 5, 6)
    }

    fun getEasyNb(filename: String) {
        val count = filename.getLines(DAY)
            .map { l -> l.split("|")[1] }
            .map { r -> r.split(" ").map { s -> s.trim() } }
            .flatten()
            .groupBy { e -> e.length }
            .filter { k -> listOf(2, 3, 4, 7).contains(k.key) }
            .map { e -> e.value }
            .sumOf { i -> i.size }

        println(count)
    }

    fun getAllNb(filename: String) {
        val result = filename.getLines(DAY).sumOf { getLineNb(it).toInt() }
        println(result)
    }

    fun getLineNb(line: String): String {
        val segments = mutableMapOf(0 to getChars(), 1 to getChars(), 2 to getChars(),
            3 to getChars(), 4 to getChars(), 5 to getChars(), 6 to getChars())

        val t = line.split(" | ").first().split(" ")
        val ee = t.map { e -> e.toList() }.flatten().groupBy { a -> a }.map { b -> b.key to b.value.size }

        segments[5] = ee.filter { k -> k.second == 9 }.map { e -> e.first }.toMutableList()
        segments[4] = ee.filter { k -> k.second == 4 }.map { e -> e.first }.toMutableList()
        segments[1] = ee.filter { k -> k.second == 6 }.map { e -> e.first }.toMutableList()

        val one = getSegmentsForNb(line, 2)[0]
        val seven = getSegmentsForNb(line, 3)
        val four = getSegmentsForNb(line, 4)

        // Segments for 1
        segments[2] = segments[2]!!.intersect(one.toMutableList()).filter { k -> !segments[5]!!.contains(k) }.toMutableList()

        // 7 has 2 out of 3 segments in common with 1 so can found one
        segments[0] = seven.first().toMutableList().subtract(segments[2]!!.plus(segments[5]!!)).toMutableList()
        // 4 has 2 out of 4 segments in common with 1
        segments[3] = four.first().toMutableList().subtract(segments[1]!!.plus(segments[2]!!).plus(segments[5]!!)).toMutableList()

        var letters = "abcdefg"
        IntRange(0, 5).forEach{ idx -> letters = letters.replace(segments[idx]?.get(0)?.toString()!!, "") }
        segments[6] = letters.toMutableList()

        val eval = digits.map { e -> e.key to e.value.joinToString("") }
            .map { s -> s.first to replaceBySegment(segments, s.second) }
            .map { e -> e.second to e.first }

        return line.split(" | ")[1].split(" ")
            .map { s -> s.toMutableList().sorted().joinToString("") }
            .map { e -> eval.first { k -> k.first == e } }
            .map { i -> i.second }
            .joinToString("")

    }

    private fun replaceBySegment(segments: MutableMap<Int, MutableList<Char>>, segment: String) =
        segment.map { c -> segments[c.toString().toInt()]?.get(0) ?: "" }
            .joinToString("").toMutableList().sorted()
            .joinToString("")

    private fun getSegmentsForNb(line: String, nb : Int) =
        line.split("|").map { s -> s.split(" ") }.flatten().filter { e -> e.length == nb }

    private fun getChars() = "abcdefg".toMutableList()
}
