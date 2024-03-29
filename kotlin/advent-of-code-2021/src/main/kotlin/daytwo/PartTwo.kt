package dev.mfazio.aoc.twentyone.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun diveWithAim(input: List<String>): Int {

    var horizontal = 0
    var depth = 0
    var aim = 0

    input.forEach { command ->
        val (direction, amount) = command.split(" ")

        when (direction) {
            "forward" -> {
                horizontal += amount.toInt()
                depth += amount.toInt() * aim
            }
            "down" -> aim += amount.toInt()
            "up" -> aim -= amount.toInt()
        }
    }

    return horizontal * depth
}

fun main() {
    val input = getResourceAsListOfStrings("daytwo.txt")

    val result = diveWithAim(input)

    println(result)
}