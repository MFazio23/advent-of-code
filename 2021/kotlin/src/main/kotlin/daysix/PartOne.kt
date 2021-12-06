package dev.mfazio.aoc.twentyone.daysix

import dev.mfazio.aoc.twentyone.util.InputHelpers
import dev.mfazio.aoc.twentyone.util.printEach
import kotlin.math.abs

fun getLanternfishCount(input: String, days: Int): Int {

    val fishList = input.split(",").map(String::toLong)

    val fullFishList = (1..days).fold(fishList) { list, ind ->
        var newFish = 0
        val result = list.map { fish ->
            if (fish == 0L) {
                newFish++
                6
            }
            else fish - 1
        }

        result + (0 until newFish).map { 8 }
    }

    return fullFishList.size
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/daysix.txt")

    input.firstOrNull()?.let { fishList ->
        val result = getLanternfishCount(fishList, 80)

        println(result)
    } ?: println("No data found!")
}