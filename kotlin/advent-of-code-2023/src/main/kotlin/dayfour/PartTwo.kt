package dev.mfazio.aoc.twentythree.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    println(
        partTwo(
            getResourceAsListOfStrings("day-four.txt")
        )
    )
}

// lol
var allTotal = 0

fun partTwoQuick(input: List<String>): Int {
    val cardResults = input.mapIndexed { index, cardLine ->
        val (winners, held) = getCardGroups(cardLine)

        val winnerCount = winners.intersect(held.toSet()).count()

        index to winnerCount
    }.toMap()

    cardResults.forEach { (game, result) ->
        getTotal(cardResults, game, result)
    }

    return allTotal
}

fun getTotal(cardResults: Map<Int, Int>, game: Int, total: Int) {
    if (!cardResults.containsKey(game) || cardResults[game] == 0) {
        allTotal++
    } else {
        allTotal++
        (1..(cardResults[game] ?: 1)).forEach { ind ->
            cardResults[game + ind]?.let { _ ->
                getTotal(cardResults, game + ind, total)
            }
        }
    }
}

/**
 * I stole this from Rolando's solution, because it's way better than mine.
 * I then made it more my style, but he still deserves the credit.
 */
fun partTwo(input: List<String>): Int =
    input.mapIndexed { index, cardLine ->
        val (winners, held) = getCardGroups(cardLine)

        val winnerCount = winners.intersect(held.toSet()).count()

        index to winnerCount
    }.fold(MutableList(input.size) { 1 }) { totals, (game, result) ->
        if (result > 0) {
            (1..result).forEach { ind ->
                totals[game + ind] += totals[game]
            }
        }
        totals
    }.sum()