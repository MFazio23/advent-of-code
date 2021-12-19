package dev.mfazio.aoc.twentyone.daythirteen

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun getCode(input: List<String>, instructionCount: Int? = null) {
    val visibleDots = getVisibleDots(input, instructionCount)
    (0..visibleDots.maxOf { it.y }).forEach { y ->
        print(y.toString().padStart(visibleDots.maxOf { it.y }.toString().length, ' '))
        (0..visibleDots.maxOf { it.x }).forEach { x ->
            print(
                if (visibleDots.any { it.x == x && it.y == y }) "#" else "."
            )
        }
        println()
    }
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/daythirteen.txt")

    getCode(input)
}