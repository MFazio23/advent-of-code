package dev.mfazio.aoc.twentythree.dayfifteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-fifteen.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val codes = input.first().split(",").map(String::hash)

    return codes.sum()
}

fun String.hash(): Int {
    var total = 0

    this.forEach { c ->
        total += c.code
        total *= 17
        total %= 256
    }

    return total
}