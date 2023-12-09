package dev.mfazio.aoc.twentythree.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-nine.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int = input.sumOf { line ->
    val differenceLines = getDifferenceLines(line)

    differenceLines.reversed().let { reversedLines ->
        var gapBetween = 0
        reversedLines.forEachIndexed { index, reversedLine ->
            gapBetween += reversedLine.last()
            differenceLines[reversedLines.size - 1 - index] = reversedLine + gapBetween
        }
    }

    differenceLines.first().last()
}

fun getDifferenceLines(line: String): MutableList<List<Int>> {
    val numberList = line.split(" ").filter { it.isNotBlank() }.map { it.toInt() }

    val differenceLines = mutableListOf(numberList)

    var differences = numberList.zipWithNext { a, b -> b - a }
    differenceLines.add(differences)

    while (!differences.all { it == 0 }) {
        differences = differences.zipWithNext { a, b -> b - a }
        differenceLines.add(differences)
    }
    return differenceLines
}
