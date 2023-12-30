package dev.mfazio.aoc.twentyone.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun dive(input: List<String>): Int {

    var horizontal = 0
    var depth = 0

    input.forEach { command ->
        val (direction, amount) = command.split(" ")

        when (direction) {
            "forward" -> horizontal += amount.toInt()
            "down" -> depth += amount.toInt()
            "up" -> depth -= amount.toInt()
        }
    }

    return horizontal * depth
}

fun main() {
    val input = getResourceAsListOfStrings("/daytwo.txt")

    val result = dive(input)

    println(result)
}