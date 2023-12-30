package dev.mfazio.aoc.twentyone.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun diagnostic(input: List<String>): Int {

    val digitCounts = (0 until (input.firstOrNull()?.length ?: 0)).map { index ->
        input.count { it[index] == '1' }
    }

    val gammaRateString = digitCounts.map { count ->
        if (input.size / 2 < count) 1 else 0
    }.joinToString("")

    val epsilonRateString = digitCounts.map { count ->
        if (input.size / 2 < count) 0 else 1
    }.joinToString("")

    return gammaRateString.toInt(2) * epsilonRateString.toInt(2)
}

fun main() {
    val input = getResourceAsListOfStrings("/daythree.txt")

    val result = diagnostic(input)

    println(result)
}