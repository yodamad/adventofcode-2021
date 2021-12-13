package aoc.day12

import aoc.shared.getLines

class Day12 {
    val DAY = "day12"
    val START = "start"
    val END = "end"

    val graph = mutableMapOf<Cave, MutableList<Cave>>()

    fun getPaths(filename: String) {
        val couples = filename.getLines(DAY).map { it.split("-") }.map { Cave(it[0]) to Cave(it[1]) }
        couples.forEach {
            if (graph.keys.contains(it.first)) graph[it.first]!!.add(it.second)
            else graph[it.first] = mutableListOf(it.second)

            if (graph.keys.contains(it.second)) graph[it.second]!!.add(it.first)
            else graph[it.second] = mutableListOf(it.first)
        }
        println(graph)

        val paths = mutableListOf<MutableList<Cave>>()
        graph.forEach {
            e -> e.value.forEach {
                c -> paths.add(mutableListOf(e.key, c))

            }
        }
    }
}

data class Cave(val label: String, var visited: Boolean = false) {
    override fun equals(other: Any?): Boolean {
        return this.label == other
    }
}

fun Pair<Cave, Cave>.contains(element : String) = this.first.label == element || this.second.label == element

fun Pair<Cave, Cave>.get(element: String) : Cave? =
    if (this.first.label == element) this.second
    else if (this.second.label == element) this.first
    else null