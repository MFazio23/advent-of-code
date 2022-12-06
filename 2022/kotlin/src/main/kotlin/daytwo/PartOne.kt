package dev.mfazio.aoc.twentytwo.daytwo

import dev.mfazio.aoc.twentytwo.util.InputHelpers

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/day-two.txt")

    println(funPartOne(input))
}

/**
 * After getting the answer, I tried to come up with a nicer way to handle the problem.
 */

sealed class RPSResult(val points: Int, val opponent: String, val player: String) {
    object Rock : RPSResult(1, "A", "X")
    object Paper : RPSResult(2, "B", "Y")
    object Scissors : RPSResult(3, "C", "Z")

    companion object {
        fun getResult(input: String) = when(input) {
            "A", "X" -> Rock
            "B", "Y" -> Paper
            "C", "Z" -> Scissors
            else -> null
        }
    }
}

fun funPartOne(input: List<String>): Int {
    val games = input.map { line ->
        val (opp, play) = line.split(" ")
        val oppResult = RPSResult.getResult(opp)
        val playerResult = RPSResult.getResult(play)

        (playerResult?.points ?: 0) + when {
            oppResult == playerResult -> 3
            oppResult is RPSResult.Rock && playerResult is RPSResult.Paper ||
            oppResult is RPSResult.Scissors && playerResult is RPSResult.Rock ||
            oppResult is RPSResult.Paper && playerResult is RPSResult.Scissors -> 6
            else -> 0
        }
    }
    return games.sum()
}