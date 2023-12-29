package dev.mfazio.aoc.twentythree.dayfourteen

import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.aoc.twentythree.printPoints
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-fourteen.txt"),
                1_000_000_000
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>, cycles: Int = 1): Int {
    val dishGrid = Point.getPointsFromInput(input)

    val allGrids = mutableMapOf<Int, List<Point<String>>>()

    val newGrid = mutableListOf<Point<String>>()

    var startingGrid = dishGrid.toList()

    val directions = listOf(
        CardinalDirection.North,
        CardinalDirection.West,
        CardinalDirection.South,
        CardinalDirection.East
    )

    val width = dishGrid.maxOf { it.x } + 1
    val height = dishGrid.maxOf { it.y } + 1

    var foundCycle = false
    var cycleIndex = 0

    while (!foundCycle) {
        repeat(4) { directionIndex ->
            newGrid.clear()

            val direction = directions[directionIndex]

            val (aRange, bRange) = when (direction) {
                CardinalDirection.North -> (0..<width) to (0..<height)
                CardinalDirection.South -> (0..<width) to (0..<height).reversed()
                CardinalDirection.East -> (0..<height) to (0..<width).reversed()
                CardinalDirection.West -> (0..<height) to (0..<width)
            }

            for (a in aRange) {
                for (b in bRange) {
                    val (x, y) = when (direction) {
                        CardinalDirection.North -> a to b
                        CardinalDirection.South -> a to b
                        CardinalDirection.East -> b to a
                        CardinalDirection.West -> b to a
                    }
                    val point = startingGrid.firstOrNull { it.x == x && it.y == y }

                    when (point?.data) {
                        "#" -> newGrid.add(point)
                        "O" -> {
                            val newX = newGrid
                                .filter { it.y == point.y }
                                .let { rowPoints ->
                                    when (direction) {
                                        CardinalDirection.West -> rowPoints.maxOfOrNull { it.x }?.let { it + 1 } ?: 0
                                        CardinalDirection.East -> rowPoints.minOfOrNull { it.x }?.let { it - 1 }
                                            ?: (width - 1)

                                        else -> point.x
                                    }
                                }
                            val newY = newGrid
                                .filter { it.x == point.x }
                                .let { columnPoints ->
                                    when (direction) {
                                        CardinalDirection.North -> columnPoints.maxOfOrNull { it.y }?.let { it + 1 }
                                            ?: 0

                                        CardinalDirection.South -> columnPoints.minOfOrNull { it.y }?.let { it - 1 }
                                            ?: (height - 1)

                                        else -> point.y
                                    }
                                }

                            newGrid.add(
                                point.copy(
                                    x = newX,
                                    y = newY
                                )
                            )
                        }
                    }
                }
            }

            startingGrid = newGrid.toList()
        }
        if (allGrids.count { (_, grid) -> grid == newGrid.toList()} >= 2) {
            println("Found a third duplicate grid at cycle ${"%,d".format(cycleIndex)} (${allGrids.size} total grids)")
            foundCycle = true
        } else {
            allGrids[cycleIndex] = newGrid.toList()
        }

        cycleIndex++
    }

    val loopGrids = allGrids.filter { (_, grid) -> allGrids.count { (_, allGrid) -> allGrid == grid } >= 2 }

    val loopIndex = loopGrids.keys.first()

    val endGrids = loopGrids.values.distinct()

    val endGrid = if (cycles <= loopIndex) {
        allGrids[cycles - 1]
    } else {
        endGrids[(cycles - loopIndex - 1) % endGrids.size]
    } ?: return -1

    return endGrid.groupBy { it.y }.map { (row, points) ->
        (height - row) * points.count { it.data == "O" }
    }.sum()
}

enum class CardinalDirection {
    North,
    South,
    East,
    West;
}