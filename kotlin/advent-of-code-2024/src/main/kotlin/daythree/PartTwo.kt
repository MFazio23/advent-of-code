package dev.mfazio.aoc.twentyfour.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-three.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

val doDontRegex = Regex("do\\(\\)((?!don't\\(\\)).)*don't\\(\\)")

fun partTwo(input: List<String>): Int {
    val fullInput = "do()${input.joinToString("")}don't()"

    return doDontRegex.findAll(fullInput).flatMap { matchResult ->
        commandRegex.findAll(matchResult.value).map { commandMatch ->
            mulRegex.findAll(commandMatch.value).map { mulMatch ->
                mulMatch.groupValues[1].toInt() * mulMatch.groupValues[2].toInt()
            }.sum()
        }
    }.sum()
}
