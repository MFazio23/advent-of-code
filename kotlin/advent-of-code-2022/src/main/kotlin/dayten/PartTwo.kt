package dev.mfazio.aoc.twentytwo.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-ten.txt")
    println(funPartTwo(input))
}

fun funPartTwo(input: List<String>): Int {
    var cycle = 1
    var sprite = listOf(0, 1, 2)
    var output = mutableListOf<String>()

    input.forEach { line ->
        if (line == "noop") {

            if (sprite.contains((cycle - 1) % 40)) {
                output.add("#")
            } else {
                output.add(".")
            }

            cycle++
        } else {
            val (_, amountString) = line.split(" ")

            val amount = amountString.toInt()

            if (sprite.contains((cycle - 1) % 40)) {
                output.add("#")
            } else {
                output.add(".")
            }

            cycle++

            if (sprite.contains((cycle - 1) % 40)) {
                output.add(cycle - 1, "#")
            } else {
                output.add(cycle - 1, ".")
            }

            cycle++

            val newAmount = (sprite[1] + amount)

            sprite = listOf(newAmount - 1, newAmount, newAmount + 1)

            println("$cycle => $sprite")
        }
    }

    output.chunked(40).forEach {
        println(it.joinToString(""))
    }

    return -1
}