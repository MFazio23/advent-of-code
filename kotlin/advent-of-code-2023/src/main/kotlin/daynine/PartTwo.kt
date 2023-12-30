package dev.mfazio.aoc.twentythree.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-nine.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>) = input.sumOf { line ->
    val differenceLines = getDifferenceLines(line)

    differenceLines.reversed().let { reversedLines ->
        var gapBetween = 0
        reversedLines.forEachIndexed { index, reversedLine ->
            gapBetween = reversedLine.first() - gapBetween
            differenceLines[reversedLines.size - 1 - index] = listOf(gapBetween) + reversedLine
        }
    }

    differenceLines.first().first()
}
