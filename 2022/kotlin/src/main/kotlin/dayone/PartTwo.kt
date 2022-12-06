package dev.mfazio.aoc.twentytwo.dayone

import dev.mfazio.aoc.twentytwo.util.InputHelpers

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/day-one.txt")

    println(getTopThreeCalorieTotals(input))
}

fun getTopThreeCalorieTotals(input: List<String>) = getCalorieTotals(input).sortedByDescending { it }.take(3).sum()