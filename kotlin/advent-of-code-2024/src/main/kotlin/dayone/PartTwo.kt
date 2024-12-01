package dev.mfazio.aoc.twentyfour.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-one.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val (firstList, secondList) = getLocationLists(input)

    val counts = firstList.map { first ->
        first to secondList.count { second -> first == second }
    }

    return counts.sumOf { (number, count) ->
        count * number
    }
}
