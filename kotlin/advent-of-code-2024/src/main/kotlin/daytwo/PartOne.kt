package dev.mfazio.aoc.twentyfour.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-two.txt"),
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val groups = input.map { it.split(Regex(" ")).map { it.toInt() } }

    return groups.count { group ->
        val zip = group.zipWithNext { a, b -> a - b }
        zip.all { (1..3).contains(abs(it)) && (zip.all { it >= 0 } || zip.all { it <= 0 }) }
    }
}

fun partBoth(input: List<String>, includeFilteredGroups: Boolean = false): Int = input
    .map { line -> line.split(Regex(" ")).map { it.toInt() } }
    .count { group ->
        checkGroup(group) || (
            includeFilteredGroups && List(group.size) { index ->
                group.subList(0, index) + group.subList(index + 1, group.size)
            }.any { checkGroup(it) }
        )
    }

fun checkGroup(group: List<Int>): Boolean {
    val zip = group.zipWithNext { a, b -> a - b }
    return zip.all { num ->
        (1..3).contains(abs(num)) && (zip.all { it >= 0 } || zip.all { it <= 0 })
    }
}