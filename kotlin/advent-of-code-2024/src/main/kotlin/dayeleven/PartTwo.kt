package dev.mfazio.aoc.twentyfour.dayeleven

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-eleven.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Long {
    val times = 75
    val stones = input.firstOrNull()?.split(" ")?.map { it.toLong() } ?: return -1

    val blinks = stones.sumOf { stone -> blink(stone, times) }

    return blinks
}

