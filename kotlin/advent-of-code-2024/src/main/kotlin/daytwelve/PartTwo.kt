package dev.mfazio.aoc.twentyfour.daytwelve

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.aoc.shared.types.calculateSides

suspend fun main() {
    runPuzzle(
        inputFileName = "day-twelve.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Long {
    val grid = Point.getPointsFromInput(input)

    println("Grid is ${grid.size} points and ${grid.maxOf { it.x }}x${grid.maxOf { it.y }}.")

    return useConnectedNeighbors(grid).sumOf { plot ->
        plot.size * plot.calculateSides(grid).toLong()
    }
}
