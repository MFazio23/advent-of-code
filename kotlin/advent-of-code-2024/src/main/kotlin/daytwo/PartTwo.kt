package dev.mfazio.aoc.twentyfour.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partBoth(
                getResourceAsListOfStrings("day-two.txt"),
                includeFilteredGroups = true,
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
    println("====== Small example =======")
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-two-small.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val groups = input.map { line -> line.split(Regex(" ")).map { it.toInt() } }

    return groups.count { group ->
        checkGroup(group) || run {
            val filteredGroups = List(group.size) { index ->
                group.subList(0, index) + group.subList(index + 1, group.size)
            }

            val result = filteredGroups.any { checkGroup(it) }

            result
        }
    }
}