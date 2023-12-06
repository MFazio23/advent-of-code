package dev.mfazio.aoc.twentythree.daysix

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach

fun main() {
    println(
        partOne(
            getResourceAsListOfStrings("day-six.txt")
        )
    )
}

fun partOne(input: List<String>): Int {
    val timeList = input.first().split("\\s+".toRegex()).drop(1).map { it.toLong() }
    val distanceList = input.last().split("\\s+".toRegex()).drop(1).map { it.toLong() }

    return timeList.zip(distanceList).map { (time, distance) ->
        (1..time).filter { holdLength ->
            (time - holdLength) * holdLength > distance
        }.size
    }.reduce { acc, i -> acc * i }
}