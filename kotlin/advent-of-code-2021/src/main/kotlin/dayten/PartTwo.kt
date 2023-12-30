package dev.mfazio.aoc.twentyone.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun calculateSyntaxIncompleteLineScore(input: List<String>): Long {

    val lines = getIncompleteLines(input)

    val remainingSymbols = lines.mapNotNull { syntaxError ->
        val queue = mutableListOf<Char>()

        syntaxError.forEach { symbol ->
            if (symbolPairs.keys.contains(symbol)) {
                queue.add(0, symbol)
            } else if (symbolPairs.values.contains(symbol) && symbolPairs[queue.first()] == symbol) {
                queue.removeAt(0)
            } else {
                return@mapNotNull null
            }
        }

        queue
    }

    val scoringSymbols = remainingSymbols.map { symbolList ->
        symbolList.map { symbol -> symbolPairs.keys.indexOf(symbol) + 1 }.fold(0L) { total, score ->
            total * 5 + score
        }
    }.sorted()

    return scoringSymbols[(scoringSymbols.size - 1) / 2]
}

fun main() {
    val input = getResourceAsListOfStrings("/dayten.txt")

    val result = calculateSyntaxIncompleteLineScore(input)

    println(result)
}