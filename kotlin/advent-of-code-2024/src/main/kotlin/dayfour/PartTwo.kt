package dev.mfazio.aoc.twentyfour.dayfour

import dev.mfazio.aoc.shared.types.NeighborType
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.aoc.shared.types.printPoints
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.isNotNullOrEmpty
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-four.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val grid = input.mapIndexed { rowInd, row ->
        row.mapIndexed { colInd, letter ->
            Point(letter.toString(), colInd, rowInd)
        }
    }.flatten()

    val count = grid.count { point ->
        point.data == "A" && isCrossMas(grid, point)
    }

    return count
}

val wantedValues = listOf("M", "S")

fun isCrossMas(grid: List<Point<String>>, point: Point<String>): Boolean {
    val neighbors = point.getDiagonalNeighbors(grid)
        .filter { (_, nPoint) -> nPoint?.data == "M" || nPoint?.data == "S" }

    val slashNeighbors = neighbors.filter { (type, _) ->
        type == NeighborType.UpperLeft || type == NeighborType.LowerRight
    }.mapNotNull { (_, nPoint) -> nPoint?.data }
    val backslashNeighbors = neighbors.filter { (type, _) ->
        type == NeighborType.UpperRight || type == NeighborType.LowerLeft
    }.mapNotNull { (_, nPoint) -> nPoint?.data }

    val isValid =
        slashNeighbors.containsAll(wantedValues) &&
        backslashNeighbors.containsAll(wantedValues)

    return isValid
}