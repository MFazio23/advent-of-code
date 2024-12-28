package dev.mfazio.aoc.twentyfour.dayeighteen

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point

suspend fun main() {
    runPuzzle(
        inputFileName = "day-eighteen.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): String {
    val width = 71
    val height = 71

    val corruption = input.map { line ->
        val (x, y) = line.split(",").map { it.toInt() }
        Point("#", x, y)
    }

    val blockingPoint = findBlockingPoint(
        startingGrid = Point.generateGrid(".", width, height),
        corruption = corruption,
        startPoint = Point(".", 0, 0),
        endPoint = Point(".", width - 1, height - 1),
        lowPoint = 1023 // We know this worked from part one
    ) ?: return "No blocking point found"

    return "${blockingPoint.x},${blockingPoint.y}"
}

fun findBlockingPoint(
    startingGrid: List<Point<String>>,
    corruption: List<Point<String>>,
    startPoint: Point<String>,
    endPoint: Point<String>,
    lowPoint: Int,
): Point<String>? {
    val checkedTimes = mutableMapOf<Int, Boolean>()
    var low = lowPoint
    var high = corruption.size - 1
    var timeToCheck = (low + high) / 2

    while (checkedTimes[timeToCheck - 1] != true && checkedTimes[timeToCheck] != false) {
        val corruptedGrid = generateCorruptedGrid(startingGrid, corruption.take(timeToCheck))

        val traversalSuccess = traverseMap(corruptedGrid, startPoint, endPoint) != null

        checkedTimes[timeToCheck] = traversalSuccess

        if (traversalSuccess) {
            low = timeToCheck
        } else {
            high = timeToCheck
        }

        timeToCheck = (low + high) / 2
    }

    return corruption[timeToCheck]
}
