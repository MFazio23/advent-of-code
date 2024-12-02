package dev.mfazio.aoc.twentyfour.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-two.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
    println("Small example")
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-two-small.txt")
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
        zip.all { (1..3).contains(abs(it)) && (zip.all { it >= 0 } || zip.all { it <= 0  }) }
    }
}
