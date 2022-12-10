package dev.mfazio.aoc.twentytwo.daysix

import dev.mfazio.aoc.twentytwo.util.InputHelpers

fun main() {
    val input = InputHelpers.getContentsFromFile("/day-six.txt")

    println(funPartOne(input))
}

fun funPartOne(input: String?): Int {

    val first = input?.windowed(14, 1, true)?.first {group ->
        group.all { letter -> group.count { it == letter } == 1}
    }

    return (input?.split(first ?: "")?.first()?.count() ?: 0) + 14
}