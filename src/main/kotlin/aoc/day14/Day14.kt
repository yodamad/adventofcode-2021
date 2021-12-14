package aoc.day14

import aoc.shared.getLines

class Day14 {

    val DAY = "day14"
    lateinit var rules: Map<String, String>

    fun makePolymer(filename: String, rounds: Int) {
        var sequence = filename.getLines(DAY).first()
        rules = filename.getLines(DAY).filter { it.contains("->") }
            .associate { r -> r.split(" -> ")[0] to r.split(" -> ")[1] }

        var chain = sequence.toMutableList()
        for (i in 0 until rounds) {
            val updatedChain = chain.mapIndexed { index, c ->
                var cc = c.toString()
                if (index < chain.toMutableList().size - 1) {
                    cc = computeRule(c.toString(), chain[index+1].toString())
                }
                cc
            }.joinToString("")
            println(updatedChain.length)

            val repartition = updatedChain.groupBy { it }.map { it.value.size }
            println("diff = ${repartition.maxOf { it } - repartition.minOf { it }}")

            chain = updatedChain.toMutableList()
        }
    }

    private fun computeRule(c1: String, c2: String): String {
        val rule = rules["$c1$c2"]
        return if (rule != null) "$c1$rule"
        else c1
    }
}