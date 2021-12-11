package dev.mfazio.aoc.twentyone.dayeleven

import dev.mfazio.aoc.twentyone.util.InputHelpers
import kotlin.math.min

fun getFlashCount(input: List<String>, stepCount: Int): Int {
    val octopi = input.flatMapIndexed { row, line -> line.mapIndexed { col, value -> Octopus(row, col, value.digitToInt()) } }
    var flashes = 0

    repeat(stepCount) {

        octopi.forEach { octopus ->
            octopus.bumpValue()
        }

        while (octopi.any { octopus -> octopus.value > 9 && !octopus.flashed }) {
            octopi.forEach { octopus ->
                if (octopus.value > 9 && !octopus.flashed) {
                    flashes++

                    listOfNotNull(
                        octopi.firstOrNull { it.col == octopus.col - 1 && it.row == octopus.row - 1 },
                        octopi.firstOrNull { it.col == octopus.col - 1 && it.row == octopus.row },
                        octopi.firstOrNull { it.col == octopus.col - 1 && it.row == octopus.row + 1 },
                        octopi.firstOrNull { it.col == octopus.col && it.row == octopus.row - 1 },
                        octopi.firstOrNull { it.col == octopus.col && it.row == octopus.row + 1 },
                        octopi.firstOrNull { it.col == octopus.col + 1 && it.row == octopus.row - 1 },
                        octopi.firstOrNull { it.col == octopus.col + 1 && it.row == octopus.row },
                        octopi.firstOrNull { it.col == octopus.col + 1 && it.row == octopus.row + 1 },
                    ).forEach { neighbor -> neighbor.bumpValue() }

                    octopus.flashed = true
                }
            }
        }

        octopi.filter { it.flashed }.forEach { octopus ->
            octopus.flashed = false
            octopus.value = 0
        }
    }

    octopi.groupBy { it.row }.map { (_, octoList) ->
        octoList.map { octopus -> octopus.value }.joinToString("")
    }

    return flashes
}

data class Octopus(
    val row: Int,
    val col: Int,
    var value: Int,
    var flashed: Boolean = false,
) {
    fun bumpValue() {
        value = min(10, value + 1)
    }
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayeleven.txt")

    val result = getFlashCount(input, 100)

    println(result)
}