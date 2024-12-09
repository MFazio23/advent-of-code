package dev.mfazio.aoc.twentyfour.dayeight

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.aoc.shared.types.printPoints
import dev.mfazio.utils.extensions.printEach

suspend fun main() {
    runPuzzle(
        inputFileName = "day-eight.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Int {
    val grid = Point.getPointsFromInput(input)

    val antennas = grid.filter { it.data != "." }

    val antennaPairs = antennas.associateWith { antenna ->
        antennas.filter { it != antenna && it.data == antenna.data }
    }

    val antinodes = grid.filter { gridPoint ->
        antennaPairs.any { (antenna, others) ->
            val antennaDistance = antenna.getDistanceTo(gridPoint)
            val otherDistances = others
                .filter { Point.arePointsInLine(listOf(it, antenna, gridPoint)) }
                .map { other -> other.getDistanceTo(gridPoint) }

            gridPoint != antenna && otherDistances.any { otherDistance ->
                antennaDistance == otherDistance * 2 ||
                otherDistance == antennaDistance * 2
            }
        }
    }

    return antinodes.size
}

