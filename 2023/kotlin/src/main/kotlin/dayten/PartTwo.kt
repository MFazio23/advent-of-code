package dev.mfazio.aoc.twentythree.dayten

import dev.mfazio.aoc.twentythree.NeighborType
import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-ten.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val points = findPointsFromInput(input).toMutableList()

    val newStarter = findNewStarter(points)

    points[points.indexOfFirst { it.data == "S" }] = newStarter

    val path = findPaths(points, newStarter).firstOrNull() ?: return -1

    val rows = points.groupBy { it.y }

    val rowCounts = rows.map { (rowNumber, rowPoints) ->
        var insideCount = 0
        var rowStateNumber = 0.0
        var lastAngle: String? = null
        rowPoints.forEach { point ->
            val inPath = path.contains(point)
            when {
                inPath && listOf("|", "S").contains(point.data) -> {
                    rowStateNumber++
                }

                inPath && listOf("L", "7").contains(point.data) -> {
                    rowStateNumber += if (lastAngle == "F" || lastAngle == "J") -0.5 else 0.5
                    lastAngle = if (rowStateNumber % 1.0 == 0.0) null else point.data
                }

                inPath && listOf("F", "J").contains(point.data) -> {
                    rowStateNumber += if (lastAngle == "L" || lastAngle == "7") -0.5 else 0.5
                    lastAngle = if (rowStateNumber % 1.0 == 0.0) null else point.data
                }

                !inPath || point.data == "." -> {
                    if (rowStateNumber.toInt() % 2 != 0) {
                        insideCount++
                    }
                }
            }
        }
        rowNumber to insideCount
    }

    return rowCounts.sumOf { (_, rowCount) -> rowCount }
}

fun findNewStarter(points: List<Point<String>>): Point<String> {
    val startingPoint = points.first { it.data == "S" }

    val neighbors = startingPoint.getBasicNeighbors(points)

    val hasTop = listOf("|", "F", "7").contains(neighbors[NeighborType.Top]?.data)
    val hasBottom = listOf("|", "L", "J").contains(neighbors[NeighborType.Bottom]?.data)
    val hasLeft = listOf("-", "L", "F").contains(neighbors[NeighborType.Left]?.data)
    val hasRight = listOf("-", "7", "J").contains(neighbors[NeighborType.Right]?.data)

    return when {
        hasTop && hasBottom -> startingPoint.copy(data = "|")
        hasLeft && hasRight -> startingPoint.copy(data = "-")
        hasTop && hasRight -> startingPoint.copy(data = "L")
        hasTop && hasLeft -> startingPoint.copy(data = "J")
        hasBottom && hasRight -> startingPoint.copy(data = "F")
        hasBottom && hasLeft -> startingPoint.copy(data = "7")
        else -> startingPoint
    }
}

fun printPoints(points: List<Point<String>>) {
    val rows = points.groupBy { it.y }

    rows.forEach { (_, rowPoints) ->
        rowPoints.forEach { point ->
            print(point.data)
        }
        println()
    }
}