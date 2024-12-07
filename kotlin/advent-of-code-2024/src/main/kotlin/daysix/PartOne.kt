package dev.mfazio.aoc.twentyfour.daysix

import dev.mfazio.aoc.shared.types.Direction
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.orZero
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-six.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val startingMap = Point.getPointsFromInput(input)

    val (startingPoint, map) = getStartingPointWithMap(startingMap) ?: return -1

    val traveledPoints = getTraveledPoints(map, startingPoint)

    return traveledPoints.size
}

fun getStartingPointWithMap(startingMap: List<Point<String>>): Pair<Point<String>, List<Point<String>>>? {
    val startingPoint = startingMap.firstOrNull { it.data == "^" } ?: return null

    return startingPoint to startingMap.map { p ->
        if (p.data == "^") {
            p.copy(data = ".")
        } else {
            p
        }
    }
}

fun getTraveledPoints(map: List<Point<String>>, startingPoint: Point<String>): List<Point<String>> {
    val traveledPoints = mutableSetOf<Point<String>>()

    var direction = Direction.Up

    var point = map.firstOrNull { it.x == startingPoint.x && it.y == startingPoint.y }

    while (point != null) {
        traveledPoints.add(point)
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

    return traveledPoints.toList()
}

fun getXYOffset(direction: Direction): Pair<Int, Int> = when (direction) {
    Direction.Up -> 0 to -1
    Direction.Down -> 0 to 1
    Direction.Left -> -1 to 0
    Direction.Right -> 1 to 0
}
