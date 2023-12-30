package dev.mfazio.aoc.twentythree.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings


fun main() {
    println(
        partTwo(
            getResourceAsListOfStrings("day-two.txt")
        )
    )
}
/**
 * This is the better version of the solution after I had more time to think.
 */
fun partTwo(input: List<String>) = parseGames(input).sumOf { it.getProduct() }

/**
 * This is the quick version I did first.
 */
fun partTwoQuick(input: List<String>): Int {
    val games = input.map { gameLine ->
        val gameId = gameIdRegex.find(gameLine)?.groupValues?.get(1)?.toIntOrNull() ?: -1

        var green = 0
        var blue = 0
        var red = 0

        val gameResults = gameLine.replace("Game $gameId: ", "")
            .split("; ")
            .map { it.split(", ").map { it.split( " ").let { (num, color) -> color to num } } }

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

    return games.sumOf { it.red * it.green * it.blue }
}