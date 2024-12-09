package dev.mfazio.aoc.twentyfour.dayeight

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point

suspend fun main() {
    runPuzzle(
        inputFileName = "day-eight.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Int {
    val grid = Point.getPointsFromInput(input)

    val antennas = grid.filter { it.data != "." }

    val antennaPairs = antennas.associateWith { antenna ->
        antennas.filter { it != antenna && it.data == antenna.data }
    }

    val antinodes = grid.filter { gridPoint ->
        antennaPairs.any { (antenna, others) ->
            others.any { Point.arePointsInLine(listOf(it, antenna, gridPoint)) }
        }
    }

    return antinodes.size
}
