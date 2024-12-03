package dev.mfazio.aoc.twentyfour.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-three.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

val commandRegex = Regex("(mul\\(\\d+,\\d+\\))")
val mulRegex = Regex("mul\\((\\d+),(\\d+)\\)")

fun partOne(input: List<String>): Int = input.flatMap { line ->
    commandRegex.findAll(line).map { matchResult ->
        mulRegex.findAll(matchResult.value).map { mulMatch ->
            mulMatch.groupValues[1].toInt() * mulMatch.groupValues[2].toInt()
        }.sum()
    }
}.sum()
