package dev.mfazio.aoc.twentyfour.daysix

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Direction
import dev.mfazio.aoc.shared.types.DirectionalPoint
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.orZero

suspend fun main() {
    runPuzzle(
        inputFileName = "day-six.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Int {

    val startingMap = Point.getPointsFromInput(input)

    val (startingPoint, map) = getStartingPointWithMap(startingMap) ?: return -1

    return getLoopCount(map, startingPoint)
}

fun getLoopCount(
    map: List<Point<String>>,
    startingPoint: Point<String>
): Int = map.count { point ->
    point.data != "#" &&
    point != startingPoint &&
    doesPointCauseLoop(map, startingPoint, point)
}

fun doesPointCauseLoop(
    startingMap: List<Point<String>>,
    startingPoint: Point<String>,
    blockedPoint: Point<String>
): Boolean {
    val traveledPoints = mutableSetOf<DirectionalPoint<String>>()

    var direction = Direction.Up

    val map = startingMap.map { p ->
        if (p == blockedPoint) {
            p.copy(data = "#")
        } else {
            p
        }
    }

    var point = map.firstOrNull { it.x == startingPoint.x && it.y == startingPoint.y }

    while (point != null) {
        val newDirectionalPoint = DirectionalPoint(point, direction)

        if (traveledPoints.contains(newDirectionalPoint)) {
            return true
        }

        traveledPoints.add(newDirectionalPoint)

        val (xOffset, yOffset) = getXYOffset(direction)
        val newPoint = map.firstOrNull { p ->
            p.x == point?.x.orZero() + xOffset && p.y == point?.y.orZero() + yOffset
        }
        if (newPoint?.data == "#") {
            direction = direction.turnRight()
        } else {
            point = newPoint
        }
    }

    return false
}