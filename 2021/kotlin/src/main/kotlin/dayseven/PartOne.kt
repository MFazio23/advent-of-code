package dev.mfazio.aoc.twentyone.dayseven

import dev.mfazio.aoc.twentyone.util.InputHelpers
import dev.mfazio.aoc.twentyone.util.printEach
import kotlin.math.abs

fun calculateCrabFuel(input: List<String>): Int {

    val values = input.map(String::toInt)

    val min = values.minOrNull()
    val max = values.maxOrNull()

    if (min == null || max == null) return -1

    val groups = (min..max).groupBy { meetingPoint ->
        values.sumOf { abs(meetingPoint - it) }
    }

    return groups.keys.minOrNull() ?: -1
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayseven.txt")

    input.firstOrNull()?.split(",")?.let { inputValues ->

        val result = calculateCrabFuel(inputValues)

        println(result)
    } ?: println("No data found!")

}