package dev.mfazio.aoc.twentytwo.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach

fun main() {
    val input = getResourceAsListOfStrings("day-ten.txt")
    println(funPartOne(input, listOf(20, 60, 100, 140, 180, 220)))
}

fun funPartOne(input: List<String>, keyCycles: List<Int>): Int {
    var cycle = 0
    var x = 1
    val keyCycleValues = mutableListOf<Int>()

    input.forEach { line ->
        if (line == "noop") {
            cycle++
            if (keyCycles.contains(cycle)) keyCycleValues.add(cycle * x)
        } else {
            val (cmd, amountString) = line.split(" ")

            cycle++

            if (keyCycles.contains(cycle)) keyCycleValues.add(cycle * x)

            println("Cycle $cycle => $x")

            cycle++

            if (keyCycles.contains(cycle)) keyCycleValues.add(cycle * x)

            x += amountString.toInt()
            println("Cycle $cycle => $x")
        }
    }

    return keyCycleValues.sum()
}