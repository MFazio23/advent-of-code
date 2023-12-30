package dev.mfazio.aoc.twentythree.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach

fun main() {
    val input = getResourceAsListOfStrings("day-two.txt")

    println(partOne(input))
}

val gameIdRegex = Regex("Game (\\d+):")

/**
 * This is the better version of the solution after I had more time to think.
 */
fun partOne(input: List<String>) = parseGames(input)
    .filter { it.isPossible() }
    .sumOf { it.id }

fun parseGames(input: List<String>) = input.map { gameLine ->
    val gameId = gameIdRegex.find(gameLine)?.groupValues?.get(1)?.toIntOrNull() ?: -1

    val gameResults = gameLine.replace("Game $gameId: ", "")
        .split("; ")
        .map { game ->
            game.split(", ").map {
                it.split(" ").let { (num, color) -> color to num }
            }
        }.flatten().groupBy { (color, _) -> color }.mapValues { (_, values) ->
            values.maxOfOrNull { (_, num) -> num.toInt() } ?: 0
        }

    Game(
        gameId,
        blue = gameResults["blue"] ?: 0,
        red = gameResults["red"] ?: 0,
        green = gameResults["green"] ?: 0,
    )
}

/**
 * This is the quick version I did first.
 */
fun partOneQuick(input: List<String>): Int {
    val games = input.map { gameLine ->
        val gameId = gameIdRegex.find(gameLine)?.groupValues?.get(1)?.toIntOrNull() ?: -1

        var green = 0
        var blue = 0
        var red = 0

        val gameResults = gameLine.replace("Game $gameId: ", "")
            .split("; ")
            .map { it.split(", ").map { it.split(" ").let { (num, color) -> color to num } } }

        gameResults.forEach { round ->
            round.forEach { (color, num) ->
                when (color) {
                    "green" -> green = maxOf(num.toInt(), green)
                    "blue" -> blue = maxOf(num.toInt(), blue)
                    "red" -> red = maxOf(num.toInt(), red)
                }
            }
        }

        Game(
            gameId,
            blue,
            red,
            green,
        )
    }

    return games.filter { it.isPossible() }.sumOf { it.id }
}

data class Game(
    val id: Int,
    val blue: Int = 0,
    val red: Int = 0,
    val green: Int = 0,

    ) {
    fun isPossible() = blue <= 14 && red <= 12 && green <= 13
    fun getProduct() = blue * red * green
}