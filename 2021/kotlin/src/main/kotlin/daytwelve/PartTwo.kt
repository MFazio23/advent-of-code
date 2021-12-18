package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/daytwelve.txt")

    val result = getPathCount(input, 2)

    println(result)
}