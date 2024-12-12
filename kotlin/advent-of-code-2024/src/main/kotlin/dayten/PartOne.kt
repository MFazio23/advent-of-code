package dev.mfazio.aoc.twentyfour.dayten

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.orZero

suspend fun main() {
    runPuzzle(
        inputFileName = "day-ten.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Int {
    val map = mapPoints(input)

    val startingPoints = map.filter { it.data == 0 }

    val pointLists = startingPoints.map { listOf(it) }

    val pathGroups = pointLists.map { findPath(map, it) }

    val distinctPathGroups = pathGroups.map { paths ->
        paths.mapNotNull { path -> path.firstOrNull { it.data == 9 } }.distinct()
    }

    return distinctPathGroups.sumOf { it.size }
}

fun mapPoints(input: List<String>): List<Point<Int>> =
    Point.getPointsFromInput(input).map { point ->
        Point(data = point.data?.toIntOrNull() ?: -1, x = point.x, y = point.y)
    }

fun findPath(map: List<Point<Int>>, pointList: List<Point<Int>>): List<List<Point<Int>>> {
    val lastPoint = pointList.last()
    val neighbors = lastPoint.getBasicNeighbors(map).filterValues { it?.data == lastPoint.data.orZero() + 1 }

    val newPoints = neighbors.map { (_, neighbor) ->
        neighbor?.let { findPath(map, pointList + neighbor) } ?: emptyList()
    }

    return if (newPoints.isNotEmpty()) {
        newPoints.flatten()
    } else listOf(pointList)
}