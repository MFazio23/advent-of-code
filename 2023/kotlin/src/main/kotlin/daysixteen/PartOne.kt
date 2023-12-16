package dev.mfazio.aoc.twentythree.daysixteen

import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-sixteen.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val points = input.mapIndexed { row, line ->
        line.split("").filter { it.isNotBlank() }.mapIndexed { col, p ->
            Point(p, col, row)
        }
    }.flatten()

    val chargedPoints = points.toMutableList().map { ChargedPoint(it) }

    var currentPoints = listOf(points.first() to Direction.Right)

    while (currentPoints.isNotEmpty()) {
        currentPoints = currentPoints.flatMap { (point, direction) ->
            chargedPoints.firstOrNull { it.point == point }?.let { chargedPoint ->
                if (chargedPoint.isCharged && chargedPoint.hitDirections.contains(direction)) {
                    return@flatMap emptyList()
                } else {
                    chargedPoint.isCharged = true
                    chargedPoint.hitDirections.add(direction)
                }
            }

            when {
                point.data == "|" && direction.isHorizontal() -> {
                    listOfNotNull(
                        points.firstOrNull { it.x == point.x && it.y == point.y - 1 }?.let {
                            it to Direction.Up
                        },
                        points.firstOrNull { it.x == point.x && it.y == point.y + 1 }?.let {
                            it to Direction.Down
                        },
                    )
                }

                point.data == "-" && direction.isVertical() -> {
                    listOfNotNull(
                        points.firstOrNull { it.x == point.x - 1 && it.y == point.y }?.let {
                            it to Direction.Left
                        },
                        points.firstOrNull { it.x == point.x + 1 && it.y == point.y }?.let {
                            it to Direction.Right
                        },
                    )
                }

                point.data == "/" && direction.isHorizontal() -> {
                    val bounceChange = if (direction == Direction.Left) 1 else -1
                    points.firstOrNull { it.x == point.x && it.y == point.y + bounceChange }?.let {
                        listOf(it to if (direction == Direction.Left) Direction.Down else Direction.Up)
                    } ?: emptyList()
                }

                point.data == "/" && direction.isVertical() -> {
                    val bounceChange = if (direction == Direction.Up) 1 else -1
                    points.firstOrNull { it.x == point.x + bounceChange && it.y == point.y }?.let {
                        listOf(it to if (direction == Direction.Up) Direction.Right else Direction.Left)
                    } ?: emptyList()
                }

                point.data == "\\" && direction.isHorizontal() -> {
                    val bounceChange = if (direction == Direction.Left) -1 else 1
                    points.firstOrNull { it.x == point.x && it.y == point.y + bounceChange }?.let {
                        listOf(it to if (direction == Direction.Left) Direction.Up else Direction.Down)
                    } ?: emptyList()
                }

                point.data == "\\" && direction.isVertical() -> {
                    val bounceChange = if (direction == Direction.Up) -1 else 1
                    points.firstOrNull { it.x == point.x + bounceChange && it.y == point.y }?.let {
                        listOf(it to if (direction == Direction.Up) Direction.Left else Direction.Right)
                    } ?: emptyList()
                }

                else -> {
                    val (xChange, yChange) = when (direction) {
                        Direction.Up -> 0 to -1
                        Direction.Down -> 0 to 1
                        Direction.Left -> -1 to 0
                        Direction.Right -> 1 to 0
                    }

                    points.firstOrNull { it.x == point.x + xChange && it.y == point.y + yChange }?.let {
                        listOf(it to direction)
                    } ?: emptyList()
                }
            }
        }
    }

    return chargedPoints.count { it.isCharged }
}

data class ChargedPoint(
    val point: Point<String>,
    var isCharged: Boolean = false,
    val hitDirections: MutableList<Direction> = mutableListOf()
)

enum class Direction {
    Up,
    Down,
    Left,
    Right;

    fun isHorizontal() = this == Left || this == Right
    fun isVertical() = this == Up || this == Down
}

fun printChargedPoints(points: List<ChargedPoint>) {
    val rows = points.groupBy { it.point.y }

    rows.forEach { (_, rowPoints) ->
        rowPoints.forEach { point ->
            print(if (point.isCharged) "#" else ".")
        }
        println()
    }
}