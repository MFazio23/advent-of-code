package dev.mfazio.aoc.twentythree.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    println(
        partTwo(
            getResourceAsListOfStrings("day-three.txt")
        )
    )
}

fun partTwo(input: List<String>): Int {
    val points = mutableSetOf<NumberSpot>()
    val symbols = mutableSetOf<Pair<Int, Int>>()
    input.forEachIndexed { row, line ->
        var currentNumber: String? = null
        var numberPoints: List<Pair<Int, Int>> = emptyList()
        line.forEachIndexed { col, spot ->
            if (spot.isDigit()) {
                currentNumber = (currentNumber ?: "") + spot
                numberPoints = numberPoints + (row to col)
            } else {
                if (currentNumber != null) {
                    points.add(
                        NumberSpot(
                            currentNumber?.toIntOrNull() ?: -1,
                            numberPoints
                        )
                    )
                }
                currentNumber = null
                numberPoints = emptyList()
                if (spot == '*') {
                    symbols.add(row to col)
                }
            }
        }

        if (currentNumber != null) {
            points.add(
                NumberSpot(
                    currentNumber?.toIntOrNull() ?: -1,
                    numberPoints
                )
            )
        }
    }

    val validGears = symbols.map { (row, col) ->
        val filteredPoints = points.filter { spot ->
            spot.points.any { (pointRow, pointCol) ->
                //TODO: Save off an "is adjacent" somewhere to be re-used. Also, probably just create a point class.
                listOf(
                    row - 1 to col - 1,
                    row - 1 to col,
                    row - 1 to col + 1,
                    row to col - 1,
                    row to col + 1,
                    row + 1 to col - 1,
                    row + 1 to col,
                    row + 1 to col + 1
                ).any { (row, col) -> pointRow == row && pointCol == col }
            }
        }

        if (filteredPoints.size == 2) {
            filteredPoints.map { it.number }.fold(1) { acc, i -> acc * i }
        } else 0
    }

    return validGears.sum()
}
