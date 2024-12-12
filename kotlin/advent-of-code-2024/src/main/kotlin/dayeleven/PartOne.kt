package dev.mfazio.aoc.twentyfour.dayeleven

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-eleven.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Int {
    val stones = input.firstOrNull()?.split(" ") ?: return -1

    var splitStones = stones.map { it.toLong() }

    repeat(25) {
        splitStones = splitStones(splitStones)
    }

    return splitStones.size
}

fun splitStones(stones: List<Long>): List<Long> = stones.flatMap { stone ->
    when {
        stone == 0L -> listOf(1)
        stone.toString().length % 2 == 0 -> {
            val stoneString = stone.toString()
            listOf(
                stoneString.substring(0, stoneString.length / 2).toLong(),
                stoneString.substring(stoneString.length / 2).toLong()
            )
        }
        else -> {
            listOf(stone * 2024)
        }
    }
}