package dev.mfazio.aoc.twentyfour.dayfourteen

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.aoc.shared.types.Quadrant
import dev.mfazio.aoc.shared.types.sortIntoQuadrants
import dev.mfazio.utils.math.product

suspend fun main() {
    runPuzzle(
        inputFileName = "day-fourteen.txt",
    ) {
        partOne(
            input = it,
            width = 101,
            height = 103,
            intervals = 100
        )
    }
}

// p=6,3 v=-1,-3
val robotRegex = Regex("p=(-?\\d+),(-?\\d+) v=(-?\\d+),(-?\\d+)")

fun partOne(input: List<String>, width: Int, height: Int, intervals: Int): Int {
    val robots = getRobots(input)

    val movedRobots = robots.map { robot ->
        robot.move(width, height, intervals)
    }

    val robotGrid = movedRobots.map { robot ->
        val count = movedRobots.count { it.x == robot.x && it.y == robot.y }
        Point(count, robot.x, robot.y)
    }

    val quadrants = robotGrid.sortIntoQuadrants(width, height)

    val quadrantSizes =
        quadrants.filter { (quadrant, _) -> quadrant != Quadrant.None }.mapValues { (_, points) -> points.size }

    return quadrantSizes.values.toList().product()
}

fun getRobots(input: List<String>): List<Robot> = input.mapNotNull { line ->
    val (x, y, vX, vY) = robotRegex.find(line)?.groupValues?.drop(1) ?: return@mapNotNull null
    Robot(x.toInt(), y.toInt(), vX.toInt(), vY.toInt())
}

data class Robot(
    val x: Int,
    val y: Int,
    val xVelo: Int,
    val yVelo: Int,
) {
    fun move(gridWidth: Int, gridHeight: Int, intervals: Int): Robot {
        val newX = (gridWidth + (x + (xVelo * intervals) % gridWidth)) % gridWidth
        val newY = (gridHeight + (y + (yVelo * intervals) % gridHeight)) % gridHeight

        return this.copy(x = newX, y = newY)
    }
}