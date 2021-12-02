package aoc.day1

import aoc.shared.getLines

class Day1 {

    val DAY = "day1"

    fun computeIncreased(filename: String) : Int {
        var increased = 0
        var previous = -1
        filename.getLines(DAY)
            .forEach{current ->
                if (previous != -1 && previous < current.toInt()) {
                    increased++
                }
                previous = current.toInt()
            }
        return increased
    }

    fun computeWindowsIncrease(filename: String) : Int {
        val windows = mutableListOf<Window>()
        var increased = 0
        filename.getLines(DAY)
            .forEachIndexed { index, current ->
                windows.add(index, Window(1, current.toInt()))
                if (index > 0) {
                    windows[index-1].size++
                    windows[index-1].total += current.toInt()
                }
                if (index > 1) {
                    windows[index-2].size++
                    windows[index-2].total += current.toInt()
                }
                if (index > 2 && windows[index-2].size == 3) {
                    if (windows[index-3].total < windows[index-2].total) increased++
                }
            }
        return increased
    }
}

data class Window(var size : Int = 0, var total : Int = 0)