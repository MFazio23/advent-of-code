package dev.mfazio.aoc.twentyone.daynine

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun calculateTotalRiskLevel(input: List<String>): Int = getLowPoints(input).sumOf { point -> point.value + 1 }

fun getLowPoints(input: List<String>) = input.mapIndexed { row, rowString ->
    rowString.mapIndexed { col, num ->
        Point(row, col, num.digitToInt()) to listOfNotNull(
            rowString.getOrNull(col - 1)?.digitToIntOrNull(),
            rowString.getOrNull(col + 1)?.digitToIntOrNull(),
            input.getOrNull(row - 1)?.getOrNull(col)?.digitToIntOrNull(),
            input.getOrNull(row + 1)?.getOrNull(col)?.digitToIntOrNull(),
        )
    }
}.flatten().filter { (point, neighbors) -> neighbors.all { it > point.value } }.map { (point, _) -> point }

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/daynine.txt")

    val result = calculateTotalRiskLevel(input)

    println(result)
}