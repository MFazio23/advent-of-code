package dev.mfazio.aoc.twentyfour.dayten

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-ten.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Int {
    val map = mapPoints(input)

    val startingPoints = map.filter { it.data == 0 }

    val pointLists = startingPoints.map { listOf(it) }

    val pathGroups = pointLists.map { findPath(map, it) }

    val distinctPathGroups = pathGroups.map { paths ->
        paths.filter { path -> path.size == 10 }.distinct()
    }

    return distinctPathGroups.sumOf { it.size }
}
