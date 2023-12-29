package dev.mfazio.aoc.twentythree.dayfourteen

import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.aoc.twentythree.printPoints
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-fourteen.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val dishGrid = Point.getPointsFromInput(input)

    val newGrid = mutableListOf<Point<String>>()

    dishGrid.forEach { point ->
        when (point.data) {
            "#" -> newGrid.add(point)
            "O" -> {
                val newY = newGrid
                    .filter { it.x == point.x }
                    .maxOfOrNull { it.y }?.let { it + 1 } ?: 0

                newGrid.add(
                    point.copy(
                        y = newY
                    )
                )
            }
        }
    }

    val columnCount = dishGrid.maxOf { it.y }

    return newGrid.groupBy { it.y }.map { (row, points) ->
        (columnCount - row + 1) * points.count { it.data == "O" }
    }.sum()
}
