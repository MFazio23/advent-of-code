package dev.mfazio.aoc.twentyone.dayten

import dev.mfazio.aoc.twentyone.util.InputHelpers

val symbolPairs = mapOf(
    '(' to ')',
    '[' to ']',
    '{' to '}',
    '<' to '>',
)

val symbolScores = mapOf(
    ')' to 3,
    ']' to 57,
    '}' to 1197,
    '>' to 25137,
)

fun calculateSyntaxErrorScore(input: List<String>): Int =
    getCorruptedSymbols(input).sumOf { symbol -> symbolScores[symbol] ?: 0 }

fun getCorruptedSymbols(input: List<String>) = input.mapNotNull { syntaxError ->
    val queue = mutableListOf<Char>()

    syntaxError.forEach { symbol ->
        if (symbolPairs.keys.contains(symbol)) {
            queue.add(0, symbol)
        } else if (symbolPairs.values.contains(symbol) && symbolPairs[queue.first()] == symbol) {
            queue.removeAt(0)
        } else {
            return@mapNotNull symbol
        }
    }

    null
}

fun getIncompleteLines(input: List<String>) = input.mapNotNull { syntaxError ->
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

    syntaxError
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayten.txt")

    val result = calculateSyntaxErrorScore(input)

    println(result)
}