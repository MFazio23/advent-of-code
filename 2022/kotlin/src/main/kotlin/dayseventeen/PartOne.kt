package dev.mfazio.aoc.twentytwo.dayseventeen

import dev.mfazio.utils.extensions.getResourceAsString

fun main() {
    val input = getResourceAsString("day-seventeen.txt")
    println(funPartOne(input, 2022))
}

fun funPartOne(input: String?, rounds: Long): Long {
    if (input == null) return -1

    val windActions = getWindActions(input)
    var windStep = 0

    val rocks = Rock.all()

    val filledPoints = mutableListOf<Point>()

    var height = 0L

    (0 until rounds).forEach { round ->
        val rock = rocks[(round % 5L).toInt()]
        var x = 2L
        var y = height + 3L

        while (true) {
            val windShift = getWindShift(windActions, windStep, rock, x, y, filledPoints)
            windStep++

            if (windStep == windActions.size) windStep = 0

            x += windShift

            if (y == 0L || rock.isHittingOtherRocks(y - 1L, x, filledPoints)) break
            y--
        }

        filledPoints.addAll(rock.getOffsetPoints(y, x))

        height = filledPoints.maxOf { it.y } + 1L
    }

    return height
}

fun getWindShift(
    windActions: List<WindAction>,
    windStep: Int,
    rock: Rock,
    currentX: Long,
    currentY: Long,
    filledPoints: List<Point>
): Int = if (windActions[windStep % windActions.size] == WindAction.Left) {
    if (rock.canMoveLeft(currentX) && !rock.isHittingOtherRocks(currentY, currentX - 1, filledPoints)) -1 else 0
} else {
    if (rock.canMoveRight(currentX) && !rock.isHittingOtherRocks(currentY, currentX + 1, filledPoints)) 1 else 0
}

enum class WindAction { Left, Right }

fun printFilledPoints(filledPoints: List<Point>) {
    val height = filledPoints.maxOf { it.y } + 1
    println("===== STATE ($height) =====")
    (filledPoints.maxOf { it.y } downTo 0).forEach { y ->
        print("|")
        (0..6).forEach { x ->
            if (filledPoints.contains(Point(y, x.toLong()))) {
                print("#")
            } else {
                print(".")
            }
        }
        println("|")
    }
    println("===== ======= =====")
}

fun getWindActions(input: String): List<WindAction> =
    input.split("").filter { it.isNotEmpty() }.map { if (it == ">") WindAction.Right else WindAction.Left }

/**
 * ####
 *
 * .#.
 * ###
 * .#.
 *
 * ..#
 * ..#
 * ###
 *
 * #
 * #
 * #
 * #
 *
 * ##
 * ##
 */
sealed class Rock(
    val filledPoints: List<Point>
) {
    class Bar : Rock(listOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(0, 3)))
    class Plus : Rock(listOf(Point(0, 1), Point(1, 0), Point(1, 1), Point(1, 2), Point(2, 1)))
    class RightL : Rock(listOf(Point(0, 0), Point(0, 1), Point(0, 2), Point(1, 2), Point(2, 2)))
    class Line : Rock(listOf(Point(0, 0), Point(1, 0), Point(2, 0), Point(3, 0)))
    class Box : Rock(listOf(Point(0, 0), Point(0, 1), Point(1, 0), Point(1, 1)))

    val width: Long = filledPoints.maxOf { it.x }
    val height: Long = filledPoints.maxOf { it.y }

    fun getOffsetPoints(offsetY: Long, offsetX: Long): List<Point> =
        filledPoints.map { Point(it.y + offsetY, it.x + offsetX) }

    fun canMoveLeft(currentX: Long) = filledPoints.none { it.x - 1 + currentX < 0 }
    fun canMoveRight(currentX: Long) = filledPoints.none { it.x + 1 + currentX > 6 }

    fun isHittingOtherRocks(offsetY: Long, offsetX: Long, filledPoints: List<Point>) =
        getOffsetPoints(offsetY, offsetX).let { offsetRock ->
            val yRange = offsetRock.minOf { it.y }..offsetRock.maxOf { it.y }
            val xRange = offsetRock.minOf { it.x }..offsetRock.maxOf { it.x }
            filledPoints
                .filter { (y, x) -> yRange.contains(y) && xRange.contains(x) }
                .any { offsetRock.contains(it) }
        }

    override fun toString(): String = (0L..width).joinToString("\n") { x ->
        (height downTo 0L).joinToString("") { y ->
            if (filledPoints.contains(Point(y, x))) "#" else "."
        }
    }

    companion object {
        fun all() = listOf(
            Bar(),
            Plus(),
            RightL(),
            Line(),
            Box()
        )
    }
}

data class Point(
    val y: Long,
    val x: Long,
)