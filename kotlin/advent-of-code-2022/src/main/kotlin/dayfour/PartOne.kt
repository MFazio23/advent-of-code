package dev.mfazio.aoc.twentytwo.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-four.txt")

    println(goodPartOne(input))
}
fun funPartOne(input: List<String>): Int {

    val groups = input.map { line ->
        val (first, second) = line.split(",")

        val (firstStart, firstEnd) = first.split("-")
        val (secondStart, secondEnd) = second.split("-")

        val firstRange = firstStart.toInt()..firstEnd.toInt()
        val secondRange = secondStart.toInt()..secondEnd.toInt()

        if (firstRange.all { secondRange.contains(it) } || secondRange.all { firstRange.contains(it) }) {
            1
        } else 0
    }.sum()

    return groups
}

fun getRangesFromInput(input: List<String>): List<Pair<IntRange, IntRange>> = input.map { line ->
    val (first, second) = line.split(",")

    val (firstStart, firstEnd) = first.split("-")
    val (secondStart, secondEnd) = second.split("-")

    val firstRange = firstStart.toInt()..firstEnd.toInt()
    val secondRange = secondStart.toInt()..secondEnd.toInt()

    firstRange to secondRange
}

fun goodPartOne(input: List<String>): Int = getRangesFromInput(input).count { (firstRange, secondRange) ->
    firstRange.all { secondRange.contains(it) } || secondRange.all { firstRange.contains(it) }
}