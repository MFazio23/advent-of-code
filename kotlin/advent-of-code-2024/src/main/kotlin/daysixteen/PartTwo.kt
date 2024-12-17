package dev.mfazio.aoc.twentyfour.daysixteen

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Direction
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.filterNotNullValues
import java.util.*
import kotlin.math.min

suspend fun main() {
    runPuzzle(
        inputFileName = "day-sixteen.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Int {
    val maze = Point.getPointsFromInput(input)

    val start = maze.firstOrNull { it.data == "S" } ?: return -1
    val end = maze.firstOrNull { it.data == "E" } ?: return -1

    val startPoint = WeightedPoint(start, Direction.Right, isVisited = false, totalValue = 0, path = listOf(start))
    val weightedPoints = mutableListOf(startPoint)

    val pointsToCheck = PriorityQueue(weightedPoints)

    val goodPaths = mutableListOf<WeightedPoint>()

    while (pointsToCheck.any()) {
        val current = pointsToCheck.poll()
        if (current.point == end) {
            goodPaths.add(current)
        }

        if (!current.isVisited) {
            current.isVisited = true
            val neighbors = current.point.getBasicNeighbors(maze).filter { (type, neighbor) ->
                val direction = type.getDirection()

                type.getOpposite().getDirection() != current.direction &&
                    neighbor?.data != "#" &&
                    weightedPoints.none {
                        it.point == neighbor && it.direction == direction && it.isVisited
                    }
            }
            neighbors.filterNotNullValues().forEach { (type, neighbor) ->
                type.getDirection()?.let { direction ->
                    val valueOffset =
                        if (type.getSides().map { it.getDirection() }.contains(current.direction)) 1001 else 1
                    val weightedNeighbor = WeightedPoint(neighbor, direction).apply {
                        path = current.path + neighbor
                    }

                    weightedPoints.add(weightedNeighbor)
                    pointsToCheck.offer(weightedNeighbor)
                    weightedNeighbor.totalValue = min(current.totalValue + valueOffset, weightedNeighbor.totalValue)
                }
            }
        }
    }

    val bestPathValue = goodPaths.minOf { it.totalValue }
    val bestPaths = goodPaths.filter { it.totalValue == bestPathValue }

    val points = bestPaths.flatMap { it.path }.distinct()

    return points.size
}
