package dev.mfazio.aoc.twentyone.dayeleven

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun getAllFlashStep(input: List<String>): Int {
    val octopi = input.flatMapIndexed { row, line -> line.mapIndexed { col, value -> Octopus(row, col, value.digitToInt()) } }
    var steps = 0

    while (true) {
        steps++

        octopi.forEach { octopus ->
            octopus.bumpValue()
        }

        while (octopi.any { octopus -> octopus.value > 9 && !octopus.flashed }) {
            octopi.forEach { octopus ->
                if (octopus.value > 9 && !octopus.flashed) {
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

        if (octopi.all { it.flashed }) return steps

        octopi.filter { it.flashed }.forEach { octopus ->
            octopus.flashed = false
            octopus.value = 0
        }
    }
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayeleven.txt")

    val result = getAllFlashStep(input)

    println(result)
}