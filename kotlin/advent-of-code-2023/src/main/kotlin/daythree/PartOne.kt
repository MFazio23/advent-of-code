package dev.mfazio.aoc.twentythree.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    println(
        partOne(
            getResourceAsListOfStrings("day-three.txt")
        )
    )
}

fun partOne(input: List<String>): Int {
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
                if (spot != '.') {
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

    val filteredPoints = points.filter { point ->
        point.points.any { (pointRow, pointCol) ->
            symbols.any { symbol ->
                val (row, col) = symbol
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
    }

    return filteredPoints.sumOf { it.number }
}

data class NumberSpot(
    val number: Int,
    val points: List<Pair<Int, Int>> = emptyList()
)