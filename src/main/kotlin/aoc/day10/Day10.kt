package aoc.day10

import aoc.shared.getLines

class Day10 {

    val DAY = "day10"

    fun findCorrupted(filename: String) {
        val result = filename.getLines(DAY).map { checkLine(it) }.sumOf { it }
        println(result)
    }

    private fun checkLine(line: String) : Int {
        val openedElmts = ArrayDeque<Char>()
        var sum = 0
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
        }
        //println(sum)
        return sum
    }

    private fun computeCharValue(c: Char) =
        when (c) {
            ')' -> 3
            ']'-> 57
            '}' -> 1197
            '>' -> 25137
            else -> 0
        }
}