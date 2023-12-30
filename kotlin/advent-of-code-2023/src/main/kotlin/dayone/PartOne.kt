package dev.mfazio.aoc.twentythree.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-one.txt")

    println(getTotalCalibrationValues(input))
}

fun getTotalCalibrationValues(input: List<String>): Int = input.sumOf { line ->
    "${line.first { it.isDigit() }}${line.last { it.isDigit() }}".toInt()
}