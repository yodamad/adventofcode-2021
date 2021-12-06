package aoc.day6

class Day6 {

    val ROUNDS = 256

    fun lanterfish(input: String) {

        val lanterns = input.split(",").map { Lanternfish(it.toInt()) }.toMutableList()

        IntRange(1, ROUNDS).forEach { _ ->
            var newLanterns = 0
            //println(lanterns)
            lanterns.map {
                when (it.life) {
                    0 -> {
                        it.life = 6
                        newLanterns++
                    }
                    else -> it.life--
                }
            }
            IntRange(1, newLanterns).forEach { _ -> lanterns.add(Lanternfish(8)) }
        }

        println(lanterns.size)
    }

    fun manyLanternfish(input: String) {
        var lanterns = input.split(",").groupBy { it.toInt() }.toList().associate { Pair(it.first, it.second.size.toLong()) }.toMutableMap()


        IntRange(1, ROUNDS).forEach { _ ->
            var newLanterns = 0L
            val tmp = mutableMapOf<Int, Long>(0 to 0L, 1 to 0L, 2 to 0L, 3 to 0L, 4 to 0L, 5 to 0L, 6 to 0L, 7 to 0L)

            lanterns.map {
                when (it.key) {
                    0 -> {
                        tmp[6] = tmp[6]!! + it.value
                        newLanterns = it.value
                    }
                    7 -> {
                        tmp[6] = tmp[6]!! + it.value
                    }
                    else -> tmp.put(it.key - 1, it.value)
                }
            }
            if (newLanterns > 0) tmp[8] = newLanterns
            //println(tmp)
            lanterns = mutableMapOf()
            lanterns.putAll(tmp)
        }
        //println(lanterns)
        println(lanterns.map { it.value }.sum())
    }
}

inline fun <T> Iterable<T>.sumByLong(selector: (T) -> Long): Long {
    return map { selector(it) }.sum()
}

data class Lanternfish(var life: Int)