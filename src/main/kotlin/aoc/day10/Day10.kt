package aoc.day10

import aoc.shared.getLines

class Day10 {

    val DAY = "day10"

    fun findCorrupted(filename: String) {
        val result = filename.getLines(DAY).map { checkLine(it) }.sumOf { it }
        println(result)
    }

    fun fillIncomplete(filename: String) {
        val incompletes = filename.getLines(DAY)
            .map { checkLine(it, true) }.filter { i -> i > -1 }
            .sorted()
        val result = incompletes[incompletes.size / 2]
        println(result)
    }

    private fun checkLine(line: String, incomplete: Boolean = false) : Long {
        val openedElmts = ArrayDeque<Char>()
        var sum = 0L
        run loop@ {
            line.forEach { c ->
                if (c in listOf('(', '[', '{', '<')) {
                    openedElmts.add(c)
                }
                if (c in listOf(')', ']', '}', '>')) {
                    when {
                        c == ')' && (openedElmts.last() != '(') -> {
                            sum += computeCharValue(c)
                            return@loop
                        }
                        c == ']' && (openedElmts.last() != '[') -> {
                            sum += computeCharValue(c)
                            return@loop
                        }
                        c == '}' && (openedElmts.last() != '{') -> {
                            sum += computeCharValue(c)
                            return@loop
                        }
                        c == '>' && (openedElmts.last() != '<') -> {
                            sum += computeCharValue(c)
                            return@loop
                        }
                        else -> openedElmts.removeLast()
                    }
                }
            }

            if (incomplete) {
                return openedElmts.reversed().map { computeCharValue(it) }.reduce { acc, v ->  acc * 5 + v }
            }
        }
        if (incomplete) return -1
        return sum
    }

    private fun computeCharValue(c: Char) : Long =
        when (c) {
            ')' -> 3
            ']'-> 57
            '}' -> 1197
            '>' -> 25137
            '(' -> 1
            '[' -> 2
            '{' -> 3
            '<' -> 4
            else -> 0
        }
}