package dev.mfazio.aoc.twentytwo.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-one.txt")

    println(getTopThreeCalorieTotals(input))
}

fun getTopThreeCalorieTotals(input: List<String>) = getCalorieTotals(input).sortedByDescending { it }.take(3).sum()