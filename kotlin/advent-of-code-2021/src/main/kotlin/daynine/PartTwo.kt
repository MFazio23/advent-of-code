package dev.mfazio.aoc.twentyone.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs

fun calculateTotalBasinProduct(input: List<String>, basinsToCount: Int): Int {

    val cave = input.flatMapIndexed { row, rowString ->
        rowString.mapIndexed { col, value -> Point(row, col, value.digitToInt()) }
    }

    val lowPoints = getLowPoints(input)

    return lowPoints.map { startPoint ->

        val checkedPoints = mutableListOf(startPoint)
        val pointsToCheck = mutableListOf(startPoint)

        while (pointsToCheck.isNotEmpty()) {
            val point = pointsToCheck.removeFirst()
            val neighbors = cave.filter { neighbor ->
                neighbor.isNeighbor(point) &&
                    neighbor.value != 9 &&
                    !checkedPoints.contains(neighbor)
            }
            checkedPoints.addAll(neighbors)
            pointsToCheck.addAll(neighbors)
        }

        checkedPoints.size
    }.sortedDescending().take(basinsToCount).reduce { total, size -> total * size }
}

data class Point(val row: Int, val col: Int, val value: Int) {
    fun isNeighbor(point: Point): Boolean =
        abs(this.row - point.row) + abs(this.col - point.col) == 1
}

fun main() {
    val input = getResourceAsListOfStrings("daynine.txt")

    val result = calculateTotalBasinProduct(input, 3)

    println(result)

}