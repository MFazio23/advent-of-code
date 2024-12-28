package dev.mfazio.aoc.twentyfour.daynineteen

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-nineteen.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Long {
    val patterns = input.firstOrNull()?.split(", ") ?: return -1

    val designs = input.drop(2)

    val designCounts = designs.map { design ->
        generateDesign(patterns, design)
    }

    return designCounts.sum()
}
