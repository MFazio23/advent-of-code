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

fun partTwo(input: List<String>): Int {
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