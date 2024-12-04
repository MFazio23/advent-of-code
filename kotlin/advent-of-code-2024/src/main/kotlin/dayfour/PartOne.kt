package dev.mfazio.aoc.twentyfour.dayfour

import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-four.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val grid = input.mapIndexed { rowInd, row ->
        row.mapIndexed { colInd, letter ->
            Point(letter.toString(), colInd, rowInd)
        }
    }.flatten()

    val count = grid.sumOf { point ->
        if (point.data == "X") {
            isXmas(grid, point)
        } else 0
    }

    return count
}

fun isXmas(grid: List<Point<String>>, point: Point<String>): Int {
    val neighbors = point.getAllNeighbors(grid)

    return neighbors.filter { (_, point) -> point?.data == "M" }.count { (type, mPoint) ->
        val nextPoint = mPoint?.nextInLine(grid, type)
        val nextNextPoint = nextPoint?.nextInLine(grid, type)

        nextPoint?.data == "A" && nextNextPoint?.data == "S"
    }
}


