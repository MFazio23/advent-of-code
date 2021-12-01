package dev.mfazio.aoc.twentyone.dayone

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun sonarSweep(measurements: List<Int>): Int = measurements.zipWithNext { a, b -> if (b > a) 1 else 0 }.sum()

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayone.txt").map(String::toInt)

    println(sonarSweep(input))
}