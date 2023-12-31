package dev.mfazio.aoc.twentytwo.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-two.txt")

    println(funPartTwo(input))
}

fun funPartTwo(input: List<String>): Int {

    val games = input.map { line ->
        val (opp, result) = line.split(" ")
        val oppResult = RPSResult.getResult(opp)

        val player = when(result) {
            "X" -> when(oppResult) {
                RPSResult.Rock -> RPSResult.Scissors
                RPSResult.Paper -> RPSResult.Rock
                RPSResult.Scissors -> RPSResult.Paper
                else -> null
            }
            "Y" -> oppResult
            "Z" -> when(oppResult) {
                RPSResult.Rock -> RPSResult.Paper
                RPSResult.Paper -> RPSResult.Scissors
                RPSResult.Scissors -> RPSResult.Rock
                else -> null
            }
            else -> null
        }

        (player?.points ?: 0) + when(result) {
            "X" -> 0
            "Y" -> 3
            "Z" -> 6
            else -> 0
        }
    }
    return games.sum()
}