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
        partOne(it)
    }
}

fun partOne(input: List<String>): Int {
    val maze = Point.getPointsFromInput(input)

    val start = maze.firstOrNull { it.data == "S" } ?: return -1
    val end = maze.firstOrNull { it.data == "E" } ?: return -1

    val startPoint = WeightedPoint(start, Direction.Right, isVisited = false, totalValue = 0)
    val weightedPoints = mutableListOf(startPoint)

    val pointsToCheck = PriorityQueue(weightedPoints)

    val validPaths = mutableListOf<WeightedPoint>()

    while (pointsToCheck.any()) {
        val current = pointsToCheck.poll()
        if (current.point == end) {
            validPaths.add(current)
        }

        if (!current.isVisited) {
            current.isVisited = true
            val neighbors = current.point.getBasicNeighbors(maze).filter { (type, neighbor) ->
                val direction = type.getDirection()

                current.direction != type.getOpposite().getDirection() &&
                neighbor?.data != "#" &&
                weightedPoints.none {
                    it.point == neighbor && it.direction == direction && it.isVisited
                }
            }
            neighbors.filterNotNullValues().forEach { (type, neighbor) ->
                type.getDirection()?.let { direction ->
                    val valueOffset = if (type.getSides().map { it.getDirection() }.contains(current.direction)) 1001 else 1
                    val weightedNeighbor = WeightedPoint(neighbor, direction)
                    weightedPoints.add(weightedNeighbor)
                    pointsToCheck.offer(weightedNeighbor)
                    weightedNeighbor.totalValue = min(current.totalValue + valueOffset, weightedNeighbor.totalValue)
                }
            }
        }
    }

    return validPaths.minOfOrNull { it.totalValue } ?: -1
}

data class WeightedPoint(
    val point: Point<String>,
    val direction: Direction,
    var isVisited: Boolean = false,
    var totalValue: Int = Int.MAX_VALUE,
    var path: List<Point<String>> = emptyList()
) : Comparable<WeightedPoint> {
    val data = point.data
    val id = "${point.id}-${direction.name}"

    override fun compareTo(other: WeightedPoint): Int = this.totalValue - other.totalValue
}