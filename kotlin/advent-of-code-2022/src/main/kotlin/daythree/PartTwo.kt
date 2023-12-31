package dev.mfazio.aoc.twentytwo.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-three.txt")

    println(goodPartTwo(input))
}

fun funPartTwo(input: List<String>): Int {
    val groups = input.chunked(3).map { (a, b, c) ->
        a.find { b.contains(it) && c.contains(it) }
    }

    return groups.filterNotNull().sumOf { type ->
        if ("[A-Z]".toRegex().matches(type.toString())) {
            type.code - 38
        } else {
            type.code - 96
        }
    }
}

fun goodPartTwo(input: List<String>): Int {
    val groups = input.chunked(3).map { (a, b, c) ->
        a.find { b.contains(it) && c.contains(it) }
    }

    return groups.filterNotNull().sumOf { type ->
        if ("[A-Z]".toRegex().matches(type.toString())) {
            type.code - 38
        } else {
            type.code - 96
        }
    }
}