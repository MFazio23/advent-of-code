package dev.mfazio.aoc.twentyone.dayfifteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import java.util.*
import kotlin.math.min

//Dijkstra's algorithm for shortest path.
fun findLeastRiskyPath(input: List<String>): Int {

    val riskMap = input.flatMapIndexed { row, rowString ->
        rowString.mapIndexedNotNull { col, valueChar ->
            if (valueChar.isDigit()) {
                RiskPoint(row, col, valueChar.digitToInt())
            } else null
        }
    }
    val startingPoint = riskMap.firstOrNull { it.row == 0 && it.col == 0 }.also { it?.totalValue = 0 } ?: return -1
    val endingPoint = riskMap.lastOrNull() ?: return -1

    val pointsToCheck = PriorityQueue(listOf(startingPoint))

    while (pointsToCheck.any()) {
        val current = pointsToCheck.poll()

        if (current == endingPoint) {
            return current.totalValue
        }

        if (!current.isVisited) {
            current.isVisited = true
            val neighbors = getNeighbors(current, riskMap)
            neighbors.forEach { neighbor ->
                pointsToCheck.offer(neighbor)
                neighbor.totalValue = min(current.totalValue + neighbor.value, neighbor.totalValue)
            }
        }
    }

    return -1
}

fun getNeighbors(current: RiskPoint, riskMap: List<RiskPoint>): List<RiskPoint> =
    listOfNotNull(
        riskMap.firstOrNull { !it.isVisited && it.row == current.row - 1 && it.col == current.col },
        riskMap.firstOrNull { !it.isVisited && it.row == current.row + 1 && it.col == current.col },
        riskMap.firstOrNull { !it.isVisited && it.row == current.row && it.col == current.col - 1 },
        riskMap.firstOrNull { !it.isVisited && it.row == current.row && it.col == current.col + 1 },
    )

data class RiskPoint(
    val row: Int,
    val col: Int,
    val value: Int,
    var isVisited: Boolean = false,
    var totalValue: Int = Int.MAX_VALUE
) : Comparable<RiskPoint> {
    override fun compareTo(other: RiskPoint): Int = this.totalValue - other.totalValue
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RiskPoint

        if (row != other.row) return false
        if (col != other.col) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + col
        return result
    }
}

fun main() {
    val input = getResourceAsListOfStrings("/dayfifteen.txt")

    val result = findLeastRiskyPath(input)

    println(result)
}