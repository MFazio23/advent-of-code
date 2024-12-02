package dev.mfazio.aoc.twentyfour.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-two.txt")
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

fun checkGroup(group: List<Int>): Boolean {
    val zip = group.zipWithNext { a, b -> a - b }
    return zip.all { num -> (1..3).contains(abs(num)) && (zip.all { it >= 0 } || zip.all { it <= 0 }) }
}