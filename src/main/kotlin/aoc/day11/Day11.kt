package aoc.day11

import aoc.shared.getLines

class Day11 {

    val DAY = "day11"
    lateinit var octopuses: List<Pair<Int, MutableList<Octopus>>>
    var flashes = 0

    fun flashOctopuses(filename: String, maxRound: Int) {
        octopuses = filename.getLines(DAY).mapIndexed { index, s -> index to s.map { Octopus(it.toString().toInt()) }.toMutableList() }

        for (round in 0 until maxRound) {
            updateOctopus()
        }
        println("Flashes = $flashes")
    }

    fun flashSimultaneous(filename: String) {
        octopuses = filename.getLines(DAY).mapIndexed { index, s -> index to s.map { Octopus(it.toString().toInt()) }.toMutableList() }
        var round = 0

        while (flashes != octopuses.size * octopuses.size) {
            flashes = 0
            updateOctopus()
            round++
            //println(octopuses)
        }
        println("Round = $round")
    }

    private fun updateOctopus() {
        val tmp = octopuses.map { pair ->
            pair.first to pair.second.map { Octopus(it.power) }.toMutableList()
        }.toMutableList()
        octopuses = tmp
        octopuses.forEachIndexed { index, pair ->
            pair.second.map { Octopus(it.power) }.forEachIndexed { idxLine, _ -> flash(idxLine, index) }
        }
    }

    private fun flash(octoIdx: Int, lineIdx: Int) {
        var updatedValue = 0
        if (octopuses[lineIdx].second[octoIdx].power == 0 && octopuses[lineIdx].second[octoIdx].flashed) {
            return
        }
        updatedValue = octopuses[lineIdx].second[octoIdx].power.plus(1)
        if (updatedValue > 9) {
            flashes++
            octopuses[lineIdx].second[octoIdx].power = 0
            octopuses[lineIdx].second[octoIdx].flashed = true
            if (goRecursive(octoIdx - 1, lineIdx - 1)) flash(octoIdx - 1, lineIdx - 1)
            if (goRecursive(octoIdx, lineIdx - 1)) flash(octoIdx, lineIdx - 1)
            if (goRecursive(octoIdx + 1, lineIdx - 1)) flash(octoIdx + 1, lineIdx - 1)
            if (goRecursive(octoIdx - 1, lineIdx)) flash(octoIdx - 1, lineIdx)
            if (goRecursive(octoIdx + 1, lineIdx)) flash(octoIdx + 1, lineIdx)
            if (goRecursive(octoIdx - 1, lineIdx + 1)) flash(octoIdx - 1, lineIdx + 1)
            if (goRecursive(octoIdx, lineIdx + 1)) flash(octoIdx, lineIdx + 1)
            if (goRecursive(octoIdx + 1, lineIdx + 1)) flash(octoIdx + 1, lineIdx + 1)
        } else octopuses[lineIdx].second[octoIdx].power = updatedValue
    }

    private fun goRecursive(octoIdx: Int, lineIdx: Int) = !(octoIdx < 0 || octoIdx >= octopuses.size
            || lineIdx < 0 || lineIdx >= octopuses.size || octopuses[lineIdx].second[octoIdx].power < 0)
}

data class Octopus(var power: Int, var flashed: Boolean = false) {
    override fun toString(): String {
        return power.toString()
    }
}