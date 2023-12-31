@file:Suppress("DuplicatedCode")

package dev.mfazio.aoc.twentytwo.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-four.txt")

    println(goodPartTwo(input))
}

fun funPartTwo(input: List<String>): Int {
    val groups = input.map { line ->
        val (first, second) = line.split(",")

        val (firstStart, firstEnd) = first.split("-")
        val (secondStart, secondEnd) = second.split("-")

        val firstRange = firstStart.toInt()..firstEnd.toInt()
        val secondRange = secondStart.toInt()..secondEnd.toInt()

        if (firstRange.any { secondRange.contains(it) } || secondRange.any { firstRange.contains(it) }) {
            1
        } else 0
    }.sum()

    return groups
}

fun goodPartTwo(input: List<String>): Int = getRangesFromInput(input).count { (firstRange, secondRange) ->
    firstRange.any { secondRange.contains(it) } || secondRange.any { firstRange.contains(it) }
}