package dev.mfazio.aoc.eighteen.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.isNotNullOrEmpty

fun main() {
    val input = getResourceAsListOfStrings("day-two.txt")

    println(partOne(input))
}


fun partOne(input: List<String>): Int {

    val twoCount = input.count { it.hasLetterCount(2) }
    val threeCount = input.count { it.hasLetterCount(3) }

    return twoCount * threeCount
}

fun String.hasLetterCount(count: Int): Boolean {
    return this.groupBy { it }
        .map { it.value.size }
        .any { it == count }
}