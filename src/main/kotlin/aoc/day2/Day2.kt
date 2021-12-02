package aoc.day2

import aoc.day2.MOVE.*
import aoc.shared.getLines

class Day2 {

    val DAY = "day2"

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

        }
    }

    fun move(filename: String): Int {
        var x = 0
        var y = 0
        filename.getLines(DAY).forEach {
            val tuple = it.split(" ")
            when (tuple[0]) {
                forward.name -> x += tuple[1].toInt()
                up.name -> y -= tuple[1].toInt()
                down.name -> y += tuple[1].toInt()
            }
        }
        return x * y
    }

    fun advancedMove(filename: String): Int {
        var x = 0
        var y = 0
        var aim = 0
        filename.getLines(DAY).forEach {
            val tuple = it.split(" ")
            when (tuple[0]) {
                forward.name -> {
                    x += tuple[1].toInt()
                    y += (tuple[1].toInt() * aim)
                }
                up.name -> aim -= tuple[1].toInt()
                down.name -> aim += tuple[1].toInt()
            }
        }
        return x * y
    }
}

enum class MOVE{forward, down, up}