package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("daytwelve.txt")

    val result = getPathCount(input, 2)

    println(result)
}