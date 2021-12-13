package aoc.day13

import aoc.day13.DIR.Companion.getDir
import aoc.day13.DIR.H
import aoc.day13.DIR.V
import aoc.shared.getLines

class Day13 {

    val DAY = "day13"
    private var plan = mutableMapOf<Int, MutableList<Int>>()
    private val update = mutableMapOf<Int, MutableList<Int>>()

    fun computeDots(filename: String) {
        val dots = filename.getLines(DAY).filter { it.isNotEmpty() && !it.startsWith("fold") }
        dots.map { it.split(",") }.map {
            if (plan[it[0].toInt()] == null) {
                plan[it[0].toInt()] = mutableListOf(it[1].toInt())
            } else {
                plan[it[0].toInt()]!!.add(it[1].toInt())
            }
        }

        println(plan.toSortedMap())

        val folds = filename.getLines(DAY).filter { it.isNotEmpty() && it.startsWith("fold") }.map { it.createFold() }
        println(folds)

        folds.forEachIndexed { idx, _ ->
            val currentFold = folds[idx]
            fold(currentFold.first, currentFold.second)
            val extract = plan.toSortedMap()
            println(extract)
            val nbDots = extract.map {
                if (currentFold.first == H) {
                    it.value.filter { e -> e < currentFold.second }.size
                } else if (it.key < currentFold.second) {
                    it.value.size
                } else { 0 }
            }.sum()
            println("nbDots = $nbDots")
        }

        plan
    }

    private fun fold(dir: DIR, size: Int) {
        val updatedPlan = mutableMapOf<Int, MutableList<Int>>()

        when (dir) {
            H -> {
                plan.forEach { e -> e.value.forEach { i -> updatedPlan.updateY(e.key, i, size) } }
            }
            V -> {
                plan.forEach { e -> e.value.forEach { i -> updatedPlan.updateX(e.key, i, size) } }
            }
        }
        plan = updatedPlan
    }
}

enum class DIR{
    H, V;

    companion object {
        fun getDir(x: String): DIR = if (x == "x") V else H
    }
}

fun String.createFold() = getDir(this.split("=")[0].split(" ")[2]) to this.split("=")[1].toInt()

fun MutableMap<Int, MutableList<Int>>.updateY(key: Int, value: Int, maxSize: Int) {
    if (value >= maxSize) {
        if (this[key] == null) {
            this[key] = mutableListOf(maxSize - (value - maxSize))
        } else if (!this[key]!!.contains(maxSize - (value - maxSize))) {
            this[key]!!.add(maxSize - (value - maxSize))
        }
    } else {
        if (this[key] == null) {
            this[key] = mutableListOf(value)
        } else if (!this[key]!!.contains(value)) {
            this[key]!!.add(value)
        }
    }
}

fun MutableMap<Int, MutableList<Int>>.updateX(key: Int, value: Int, maxSize: Int) {
    if (key >= maxSize) {
        if (this[maxSize - (key - maxSize)] == null) {
            this[maxSize - (key - maxSize)] = mutableListOf(value)
        } else if (!this[maxSize - (key - maxSize)]!!.contains(value)) {
            this[maxSize - (key - maxSize)]!!.add(value)
        }
    } else {
        if (this[key] == null) {
            this[key] = mutableListOf(value)
        } else if (!this[key]!!.contains(value)) {
            this[key]!!.add(value)
        }
    }
}