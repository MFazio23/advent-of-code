package dev.mfazio.aoc.twentyone.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun calculateExpensiveCrabFuel(input: List<String>): Int {

    val values = input.map(String::toInt)

    val min = values.minOrNull()
    val max = values.maxOrNull()

    if (min == null || max == null) return -1

    val groups = (min..max).groupBy { meetingPoint ->
        values.sumOf {
            val (mpMin, mpMax) = if (meetingPoint > it) 1 to meetingPoint - it else 1 to it - meetingPoint

            (mpMin..mpMax).sum()
        }
    }

    return groups.keys.minOrNull() ?: -1
}

fun main() {
    val input = getResourceAsListOfStrings("dayseven.txt")

    input.firstOrNull()?.split(",")?.let { inputValues ->

        val result = calculateExpensiveCrabFuel(inputValues)

        println(result)
    } ?: println("No data found!")

}