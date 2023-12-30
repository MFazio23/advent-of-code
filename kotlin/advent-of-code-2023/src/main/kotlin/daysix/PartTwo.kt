package dev.mfazio.aoc.twentythree.daysix

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach

fun main() {
    println(
        partTwo(
            getResourceAsListOfStrings("day-six.txt")
        )
    )
}

fun partTwo(input: List<String>): Int {
    val time = input.first().split("\\s+".toRegex()).drop(1).joinToString("").toLong()
    val distance = input.last().split("\\s+".toRegex()).drop(1).joinToString("").toLong()

    return (1..time).filter { holdLength ->
        (time - holdLength) * holdLength > distance
    }.size

}
