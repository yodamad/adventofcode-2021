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
        val segments = mutableMapOf(0 to getChars(), 1 to getChars(), 2 to getChars(),
            3 to getChars(), 4 to getChars(), 5 to getChars(), 6 to getChars())

        val one = getSegmentsForNb(filename, 2)[0]
        val seven = getSegmentsForNb(filename, 3)
        val four = getSegmentsForNb(filename, 4)

        // Segments for 1
        segments[2] = segments[2]!!.intersect(one.toMutableList()).toMutableList()
        segments[5] = segments[2]!!.toMutableList()
        // 7 has 2 out of 3 segments in common with 1 so can found one
        segments[0] = seven.first().toMutableList().filter { e ->
            !segments[2]!!.contains(e) } as MutableList<Char>
        // 4 has 2 out of 4 segments in common with 1
        segments[1] = four.first().toMutableList().filter { f ->
            !segments[2]!!.contains(f) && f.toString() != "" } as MutableList<Char>
        segments[3] = segments[1]!!.toMutableList()

        segments[4] = segments[4]!!.filter { c ->
            !segments[2]!!.contains(c)
                    && !segments[1]!!.contains(c)
                    && !segments[0]!!.contains(c) } as MutableList<Char>
        segments[6] = segments[4]!!.toMutableList()

        println(segments)

        digits.map { e -> e.key to e.value.joinToString("") }
            .map { s -> s.first to replaceBySegment(segments, s.second) }
    }

    private fun replaceBySegment(segments: MutableMap<Int, MutableList<Char>>, segment: String) {
        val s = segments.filter { it.value.size == 1 }
            .map { e -> segment.replace(e.key.toString(), segments[e.key]!!.joinToString("")) }.toString()
        println(s)
    }

    private fun getSegmentsForNb(filename: String, nb : Int) =
        filename.getLines(DAY).first().split("|").map { s -> s.split(" ") }
        .flatten().filter { e -> e.length == nb }

    private fun getChars() = "abcdefg".toMutableList()
}
