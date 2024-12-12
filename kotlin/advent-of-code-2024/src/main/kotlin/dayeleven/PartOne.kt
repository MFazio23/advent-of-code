package dev.mfazio.aoc.twentyfour.dayeleven

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-eleven.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Long {
    val times = 25
    val stones = input.firstOrNull()?.split(" ")?.map { it.toLong() } ?: return -1

    val blinks = stones.sumOf { stone -> blink(stone, times) }

    return blinks
}

// The cache and blink idea was taken from Todd Ginsberg's solution
// https://todd.ginsberg.com/post/advent-of-code/2024/day11/
private val cache = mutableMapOf<CacheKey, Long>()

fun blink(
    stone: Long,
    blinkCount: Int,
    key: CacheKey = stone to blinkCount
): Long = when {
    blinkCount == 0 -> 1
    key in cache -> cache.getValue(key)
    else -> {
        val result = when {
            stone == 0L -> blink(1, blinkCount - 1)
            stone.hasEvenDigits() -> stone.halves().sumOf { blink(it, blinkCount - 1) }
            else -> {
                blink(stone * 2024, blinkCount - 1)
            }
        }
        cache[key] = result
        result
    }
}

// This was my original solution. It does not scale.
fun partOneQuick(input: List<String>): Int {
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
        stone.hasEvenDigits() -> stone.halves()
        else -> {
            listOf(stone * 2024)
        }
    }
}

private fun Long.hasEvenDigits() = this.toString().length % 2 == 0

private fun Long.halves() = this.toString().let { stoneString ->
    listOf(
        stoneString.substring(0, stoneString.length / 2).toLong(),
        stoneString.substring(stoneString.length / 2).toLong()
    )
}

typealias CacheKey = Pair<Long, Int>