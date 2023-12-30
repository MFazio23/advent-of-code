package dev.mfazio.aoc.eighteen.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.isNotNullOrEmpty

fun main() {
    val input = getResourceAsListOfStrings("day-one.txt")

    println(partOne(input))
}


fun partOne(input: List<String>): Int {
    return input.filter { it.isNotNullOrEmpty() }.sumOf { it.toInt() }
}