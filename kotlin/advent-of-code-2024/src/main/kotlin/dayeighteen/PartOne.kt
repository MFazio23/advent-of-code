package dev.mfazio.aoc.twentyfour.dayeighteen

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.orZero
import java.util.*

suspend fun main() {
    runPuzzle(
        inputFileName = "day-eighteen.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Int {
    val width = 71
    val height = 71
    val result = generateGridAndFindPath(input, width, height, 1024)

    return result?.filter { (point, _) -> point.x == width - 1 && point.y == height - 1 }?.values?.maxOrNull().orZero()
}

fun generateGrid(input: List<String>, width: Int, height: Int, corruptionTime: Int): List<Point<String>> {
    val corruption = input.map { line ->
        val (x, y) = line.split(",").map { it.toInt() }
        Point("#", x, y)
    }.take(corruptionTime)

    return generateCorruptedGrid(Point.generateGrid(".", width, height), corruption)
}

fun generateCorruptedGrid(startingGrid: List<Point<String>>, corruption: List<Point<String>>): List<Point<String>> =
    startingGrid.map { point ->
        if (corruption.any { it.isSameLocation(point) }) {
            Point("#", point.x, point.y)
        } else {
            point
        }
    }

fun generateGridAndFindPath(input: List<String>, width: Int, height: Int, corruptionTime: Int): Map<Point<String>, Int>? {
    val grid = generateGrid(input, width, height, corruptionTime)

    val start = grid.firstOrNull { it.x == 0 && it.y == 0 } ?: return emptyMap()
    val end = grid.firstOrNull { it.x == width - 1 && it.y == height - 1 } ?: return emptyMap()

    val traversalResult = traverseMap(grid, start, end)

    return traversalResult
}

fun traverseMap(map: List<Point<String>>, start: Point<String>, end: Point<String>): Map<Point<String>, Int>? {
    val visitedPoints = mutableListOf<Point<String>>()
    val pointValues = map.associateWith { point ->
        if (point.isSameLocation(start)) 0 else Int.MAX_VALUE
    }.toMutableMap()

    val pointsToCheck = PriorityQueue<Pair<Point<String>, Int>>(compareBy { (_, score) -> score }).apply {
        add(start to 0)
    }

    while (pointsToCheck.any()) {
        val (current, score) = pointsToCheck.poll()

        if (current.isSameLocation(end)) {
            return pointValues.filter { (point, _) -> point.data != "#" }
        }

        if (!visitedPoints.contains(current)) {
            visitedPoints.add(current)
            val neighbors = current.getBasicNeighbors(map)
                .mapNotNull { (_, point) ->
                    map.firstOrNull { point != null && it.isSameLocation(point) }
                }
                .filter { neighbor ->
                    neighbor.data != "#" && !visitedPoints.contains(neighbor)
                }
                .onEach { point ->
                    pointValues[point] = minOf(
                        pointValues[point] ?: Int.MAX_VALUE,
                        pointValues[current].orZero() + 1
                    )
                }

            neighbors.forEach { neighbor ->
                pointsToCheck.offer(neighbor to score + 1)
            }
        }
    }

    return null
}