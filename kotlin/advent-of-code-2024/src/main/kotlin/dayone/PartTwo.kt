package dev.mfazio.aoc.twentyfour.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwoQuick(
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

fun partTwoQuick(input: List<String>): Int {
    val firstList = mutableListOf<Int>()
    val secondList = mutableListOf<Int>()
    input.forEach { line ->
        val (first, second) = line.split("   ").map { it.toInt() }
        firstList.add(first)
        secondList.add(second)
    }

    val counts = firstList.associateWith { first ->
        val count = secondList.count { second -> first == second }

        count
    }

    return counts.map { (number, count) ->
        count * number
    }.sum()
}
