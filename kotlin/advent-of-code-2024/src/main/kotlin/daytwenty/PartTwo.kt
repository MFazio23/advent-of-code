package dev.mfazio.aoc.twentyfour.daytwenty

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-twenty.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Int = getCheatCounts(input, 20, 100)