package dev.mfazio.aoc.twentyfour.daytwenty

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.crossProduct
import dev.mfazio.utils.extensions.getOrZero
import dev.mfazio.utils.extensions.orZero
import java.util.*
import kotlin.math.abs

suspend fun main() {
    runPuzzle(
        inputFileName = "day-twenty.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Int = getCheatCounts(input, 2, 100)

fun partOneTooMuch(input: List<String>): Int {
    val map = Point.getPointsFromInput(input)

    val start = map.firstOrNull { it.data == "S" } ?: return -1
    val end = map.firstOrNull { it.data == "E" } ?: return -1

    val baseValues = traverseMap(map, start, end)

    val innerWalls = map.filter { point ->
        point.data == "#" &&
        (1 until map.maxOf { it.x }).contains(point.x) &&
        (1 until map.maxOf { it.y }).contains(point.y)
    }

    val cheatWalls = innerWalls.map { wallPoint ->
        val neighbors = wallPoint.getBasicNeighbors(map).filter { (_, neighbor) ->
            baseValues.keys.contains(neighbor)
        }.values.toList().filterNotNull()

        if (neighbors.size < 2) return@map 0

        val diff = neighbors.crossProduct(neighbors).maxOf { (first, second) ->
            abs(baseValues.getOrZero(first) - baseValues.getOrZero(second))
        }

        if (diff == 0) 0 else diff - 2 // Subtract 2 for the wall itself
    }.filter { it > 0 }

    println("Cheat wall count=${cheatWalls.size}")

    val cheatCounts = cheatWalls.groupBy { it }.mapValues { (_, walls) -> walls.size }

    return cheatCounts.toList().filter { (diff, _) -> diff >= 100 }.sumOf { (_, count) -> count }
}

fun getCheatCounts(input: List<String>, allowedCheatDistance: Int, properSavings: Int): Int {
    val map = Point.getPointsFromInput(input)

    val start = map.firstOrNull { it.data == "S" } ?: return -1
    val end = map.firstOrNull { it.data == "E" } ?: return -1

    val baseValues = traverseMap(map, start, end)

    val cheats = baseValues.flatMap { (point, cost) ->
        baseValues.mapNotNull { (otherPoint, otherCost) ->
            val distance = point.getDistanceTo(otherPoint)

            if (distance in (1 .. allowedCheatDistance)) {
                RaceCheat(
                    point,
                    otherPoint,
                    point.getDistanceTo(otherPoint),
                    abs(cost - otherCost) - distance
                )
            } else null
        }
    }

    val validCheats = cheats
        .distinctBy { cheat -> listOf(cheat.pointA.coordinates, cheat.pointB.coordinates).sorted() }
        .filter { cheat -> cheat.savings >= properSavings }

    return validCheats.size
}

// This is overkill, but it _does_ work.
fun traverseMap(map: List<Point<String>>, start: Point<String>, end: Point<String>): Map<Point<String>, Int> {
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

    return emptyMap()
}

data class RaceCheat(
    val pointA: Point<String>,
    val pointB: Point<String>,
    val distance: Int,
    val savings: Int,
)
