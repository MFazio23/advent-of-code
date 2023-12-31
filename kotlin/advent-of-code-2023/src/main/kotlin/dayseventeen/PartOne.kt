package dev.mfazio.aoc.twentythree.dayseventeen

import dev.mfazio.aoc.shared.types.NeighborType
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.aoc.shared.types.printPoints
import dev.mfazio.aoc.twentythree.daysixteen.Direction
import dev.mfazio.utils.extensions.filterNotNullValues
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.orZero
import java.util.*
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-seventeen.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val points = input.mapIndexed { y, line ->
        line.mapIndexed { x, d ->
            Point(d.digitToInt(), x, y)
        }
    }.flatten()

    val startingPoint = points.first()
    val endPoint = points.last()

    points.printPoints()

    val shortestPath = shortestPath(
        points,
        startingPoint
    ) { it == endPoint }

    return shortestPath
}

fun shortestPath(
    points: List<Point<Int>>,
    startingPoint: Point<Int>,
    isEnd: (Point<Int>) -> Boolean,
): Int {
    val seen = mutableMapOf<Point<Int>, VisitedPoint>()
    val seenHeatLossPoints = mutableSetOf<PointHeatLoss>()
    val queue = PriorityQueue(listOf(PointHeatLoss(startingPoint, 0, emptyList())))

    while (queue.isNotEmpty()) {
        val nextPoint = queue.poll()
        println(nextPoint)

        seenHeatLossPoints += nextPoint
        val neighbors = nextPoint.point.getBasicNeighbors(points)
            .filter { (_, point) -> point !in seen }
            .mapKeys { (neighborType, _) ->
                val direction = when (neighborType) {
                    NeighborType.Top -> Direction.Up
                    NeighborType.Bottom -> Direction.Down
                    NeighborType.Left -> Direction.Left
                    NeighborType.Right -> Direction.Right
                }
                direction
            }
            .filter { (direction, _) ->
                val lastThree = nextPoint.path.takeLast(3)

                lastThree.size < 3 || !lastThree.all { it == direction }
            }
        neighbors.firstNotNullOfOrNull { (direction, point) ->
            if (point != null && isEnd(point)) direction to point else null
        }?.let { (direction, endPoint) ->
            seenHeatLossPoints += PointHeatLoss(
                point = endPoint,
                heatLoss = nextPoint.heatLoss + endPoint.data.orZero(),
                path = nextPoint.path + direction
            ).also {
                println(it)
            }
            println()
            points.printPoints()
            return nextPoint.heatLoss + endPoint.data.orZero()
        }
        val nextNeighbors = neighbors
            .filterNotNullValues()
            .map { (direction, point) ->
                PointHeatLoss(
                    point = point,
                    heatLoss = nextPoint.heatLoss + point.data.orZero(),
                    path = nextPoint.path + direction
                )
            }
        nextNeighbors.forEach { seen[it.point] = VisitedPoint(it.heatLoss, nextPoint.point) }
        queue.addAll(nextNeighbors)
    }

    return -1
}

data class VisitedPoint(
    val heatLoss: Int,
    val lastPoint: Point<Int>? = null,
)

data class PointHeatLoss(
    val point: Point<Int>,
    val heatLoss: Int,
    val path: List<Direction>
) : Comparable<PointHeatLoss> {
    override fun compareTo(other: PointHeatLoss): Int = this.heatLoss.compareTo(other.heatLoss)
}