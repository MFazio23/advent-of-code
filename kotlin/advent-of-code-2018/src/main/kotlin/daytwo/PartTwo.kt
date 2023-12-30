package dev.mfazio.aoc.eighteen.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.isNotNullOrEmpty


fun main () {
    println(
        partTwo(
            getResourceAsListOfStrings("day-two.txt")
        )
    )
}

fun partTwo(input: List<String>): String {
    input.forEach {
        val off = input.firstOrNull { other ->
            it.offByOneChar(other)
        }
        if (off.isNotNullOrEmpty()) {
            println("Off=$off ($it)")
            return it.filterIndexed { index, c -> c == off!![index] }
        }
    }
    return "???"
}

fun String.offByOneChar(other: String): Boolean {
    var matchingLetters = 0
    other.forEachIndexed { index, c ->
        if (this[index] == c) {
            matchingLetters++
        }
    }

    return matchingLetters == this.length - 1
}