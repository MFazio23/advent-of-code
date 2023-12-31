package dev.mfazio.aoc.twentyone.daysix

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun getEfficientLanternfishCount(input: String, days: Int): Long {

    val initialFish = input.split(",").map(String::toInt)

    val fishCounts = (0..8).map { ind -> initialFish.count { it == ind }.toLong() }

    val resultFish = (1..days).fold(fishCounts) { counts, _ ->
        val newFish = counts[0]
        counts.drop(1).toMutableList().also {
            it[6] += newFish
        } + newFish
    }

    return resultFish.sum()
}

fun main() {
    val input = getResourceAsListOfStrings("daysix.txt")

    input.firstOrNull()?.let { fishList ->
        val result = getEfficientLanternfishCount(fishList, 256)

        println(result)
    } ?: println("No data found!")
}