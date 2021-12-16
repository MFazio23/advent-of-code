package dev.mfazio.aoc.twentyone.dayfifteen

import dev.mfazio.aoc.twentyone.util.InputHelpers
import java.util.*
import kotlin.math.min
import kotlin.system.measureTimeMillis

fun findLeastRiskyPathInFiveX(input: List<String>): Int {
    val riskMapHeight = input.size
    val riskMapWidth = input.firstOrNull()?.trim()?.length ?: return -1
    val riskMap = input.flatMapIndexed { row, rowString ->
        rowString.mapIndexed { col, valueChar ->
            (0 until 5).flatMap { rowFactor ->
                (0 until 5).mapNotNull { colFactor ->
                    if (valueChar.isDigit()) {
                        RiskPoint(
                            row + (rowFactor * riskMapHeight),
                            col + (colFactor * riskMapWidth),
                            (valueChar.digitToInt() + rowFactor + colFactor).let {
                                if (it > 9) it - 9 else it
                            }
                        )
                    } else null
                }
            }
        }.flatten()
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

fun main() {
    val timing = measureTimeMillis {
        val input = InputHelpers.getListOfStringsFromFile("/dayfifteen.txt")

        val result = findLeastRiskyPathInFiveX(input)

        println(result)
    }

    println("Time taken: $timing ms")
}