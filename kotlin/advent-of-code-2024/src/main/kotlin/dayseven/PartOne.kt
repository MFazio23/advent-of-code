package dev.mfazio.aoc.twentyfour.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-seven.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Long {
    val equations = getEquations(input)

    val validEquations = getValidEquations(equations)

    return validEquations.sumOf { (value, _) -> value }
}

fun getValidEquations(equations: List<Pair<Long, List<Long>>>) =
    equations.filter { (testValue, testNumbers) ->
        testNumbers.fold(List(size = testNumbers.size) { 0L }) { acc, number ->
            acc.flatMap { accNumber ->
                listOf(
                    accNumber + number,
                    accNumber * number,
                )
            }
        }.contains(testValue)
    }

fun getEquations(input: List<String>) = input.map { line ->
    val (testValueString, testNumbersString) = line.split(":")
    val numbers = testNumbersString.trim().split(" ").map { it.toLong() }

    testValueString.toLong() to numbers
}
