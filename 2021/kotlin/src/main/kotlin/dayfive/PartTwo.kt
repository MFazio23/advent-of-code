package dev.mfazio.aoc.twentyone.dayfive

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun hydrothermalVentureDiagonal(input: List<String>): Int {

    val vents = input.map { HydrothermalVent.convertStringToVent(it) }
    val points = vents.mapNotNull { vent -> vent?.getVentLinesPlusDiagonal() }
    return points
        .flatten()
        .groupingBy { it }
        .eachCount()
        .count { it.value > 1 }
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayfive.txt")

    val result = hydrothermalVentureDiagonal(input)

    println(result)
}