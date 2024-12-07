package dev.mfazio.aoc.twentyfour.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-seven.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Long {
    val equations = getEquations(input)

    val validEquations = getConcatValidEquations(equations)
    return validEquations.sumOf { (value, _) -> value }
}

fun getConcatValidEquations(equations: List<Pair<Long, List<Long>>>) =
    equations.filter { (testValue, testNumbers) ->
        testNumbers.fold(List(size = testNumbers.size) { 0L }) { acc, number ->
            acc.flatMap { accNumber ->
                listOf(
                    accNumber + number,
                    accNumber * number,
                    (accNumber.toString() + number.toString()).toLong()
                )
            }
        }.contains(testValue)
    }