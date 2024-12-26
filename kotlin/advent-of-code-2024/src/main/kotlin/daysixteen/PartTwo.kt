package dev.mfazio.aoc.twentyfour.daysixteen

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Direction
import dev.mfazio.aoc.shared.types.Point
import java.util.*

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
    val goodPathPoints = mutableSetOf<Point<String>>()
    val pointsToCheck = PriorityQueue(weightedPoints)
    val visitedPointValues = mutableMapOf<String, Int>()
    var endPathCost: Int? = null

    while (pointsToCheck.isNotEmpty()) {
        val point = pointsToCheck.poll()

        when {
            endPathCost != null && point.totalValue > endPathCost -> {
                return goodPathPoints.size
            }

            point.point == end -> {
                endPathCost = point.totalValue
                goodPathPoints.addAll(point.path)
            }

            visitedPointValues.getOrDefault(point.id, Int.MAX_VALUE) >= point.totalValue -> {
                visitedPointValues[point.id] = point.totalValue
                val neighbors = point.point.getBasicNeighbors(maze).mapNotNull { neighbor ->
                    val (type, neighborPoint) = neighbor
                    val direction = type.getDirection()

                    when {
                        neighborPoint == null || direction == null -> null
                        type.getOpposite().getDirection() == point.direction -> null
                        neighborPoint.data == "#" -> null
                        direction == point.direction -> WeightedPoint(
                            neighborPoint,
                            direction,
                            totalValue = point.totalValue + 1,
                            path = point.path + neighborPoint
                        )
                        else -> WeightedPoint(
                            neighborPoint,
                            direction,
                            totalValue = point.totalValue + 1001,
                            path = point.path + neighborPoint
                        )
                    }
                }

                pointsToCheck.addAll(neighbors)
            }
        }
    }

    return goodPathPoints.size
}

