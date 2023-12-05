package dev.mfazio.aoc.twentythree.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.isNotNullOrEmpty
import kotlin.math.pow

fun main() {
    println(
        partOne(
            getResourceAsListOfStrings("day-four.txt")
        )
    )
}

fun partOne(input: List<String>): Int = input.sumOf { cardLine ->
    val (winners, held) = getCardGroups(cardLine)

    val winnerCount = winners.intersect(held.toSet()).count()

    if (winnerCount == 0) 0 else (2.0.pow(winnerCount - 1)).toInt()
}

fun getCardGroups(cardLine: String): Pair<List<Int>, List<Int>> {
    val cardParts = cardLine.split(Regex(":\\s+"))
    val (winnerString, heldString) = cardParts[1].split(" | ")
    val winners = winnerString.split(Regex("\\s+")).filter { it.isNotNullOrEmpty() }.map { it.toInt() }
    val held = heldString.split(Regex("\\s+")).filter { it.isNotNullOrEmpty() }.map { it.toInt() }

    return winners to held
}