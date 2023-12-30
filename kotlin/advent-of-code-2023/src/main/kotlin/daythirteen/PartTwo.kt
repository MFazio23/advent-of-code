package dev.mfazio.aoc.twentythree.daythirteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-thirteen.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int = getBlocks(input).sumOf { block ->
    findReflectionTotal(block, 1)
}