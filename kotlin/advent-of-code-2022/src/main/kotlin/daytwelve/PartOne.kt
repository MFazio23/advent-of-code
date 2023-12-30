package dev.mfazio.aoc.twentytwo.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import java.util.*
import kotlin.math.min

fun main() {
    val input = getResourceAsListOfStrings("day-twelve.txt")
    println(funPartOne(input))
}
fun funPartOne(input: List<String>): Int {
    val map = getMap(input)

    val startingSquare = map.firstOrNull { it.isStart } ?: return -1
    val endingSquare = map.firstOrNull { it.isEnd } ?: return -1

    val squaresToCheck = PriorityQueue(listOf(startingSquare))

    while (squaresToCheck.any()) {
        val current = squaresToCheck.poll()

        if (current == endingSquare) {
            return current.totalValue
        }

        if (!current.isVisited) {
            current.isVisited = true
            current.getValidNeighbors(map).forEach { neighbor ->
                squaresToCheck.offer(neighbor)
                neighbor.totalValue = min(current.totalValue + 1, neighbor.totalValue)
            }
        }
    }

    return -1
}
fun getMap(input: List<String>): List<GridSquare> {
    val elevations = ('a'..'z').mapIndexed { i, c -> c to i + 1 }.toMap()
    return input.flatMapIndexed { row, line ->
        line.mapIndexed { col, gridSquare ->
            val isStart = gridSquare == 'S'
            val isEnd = gridSquare == 'E'
            val height = when {
                isStart -> elevations['a']
                isEnd -> elevations['z']
                else -> elevations[gridSquare]
            }
            GridSquare(
                height = height ?: -1,
                row = row,
                col = col,
                isStart = isStart,
                isEnd = isEnd,
            )
        }
    }.filter { it.isValid }
}

data class GridSquare(
    val height: Int,
    val row: Int,
    val col: Int,
    val isStart: Boolean = false,
    val isEnd: Boolean = false,
    var isVisited: Boolean = false,
    var totalValue: Int = Int.MAX_VALUE,
) : Comparable<GridSquare> {
    val isValid = height > 0 || isStart || isEnd

    fun canMoveTo(otherSquare: GridSquare) = otherSquare.height <= height + 1

    fun getValidNeighbors(map: List<GridSquare>): List<GridSquare> =
        listOfNotNull(
            map.firstOrNull { it.row == row && it.col == col + 1 },
            map.firstOrNull { it.row == row && it.col == col - 1 },
            map.firstOrNull { it.row == row + 1 && it.col == col },
            map.firstOrNull { it.row == row - 1 && it.col == col },
        ).filter { !it.isVisited && canMoveTo(it) }

    override fun compareTo(other: GridSquare): Int = totalValue - other.totalValue
}