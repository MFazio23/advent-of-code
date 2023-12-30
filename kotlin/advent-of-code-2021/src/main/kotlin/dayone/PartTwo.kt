package dev.mfazio.aoc.twentyone.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

/*
    A: 607 (N/A - no previous sum)
    B: 618 (increased)
    C: 618 (no change)
    D: 617 (decreased)
    E: 647 (increased)
    F: 716 (increased)
    G: 769 (increased)
    H: 792 (increased)
*/
fun sonarSweepTriple(measurements: List<Int>): Int {
    val measurementSums = measurements.mapIndexed { index, measurement ->
        if (index >= 2) {
            (0..2).sumOf { measurements[index - it] }
        } else -1
    }.filter { it > 0 }

    return sonarSweep(measurementSums)
}

fun sonarSweepWindowedTriple(measurements: List<Int>): Int =
    sonarSweep(measurements.windowed(3, 1).map { it.sum() })

fun main() {
    val input = getResourceAsListOfStrings("/dayone.txt").map(String::toInt)

    println(sonarSweepTriple(input))
}