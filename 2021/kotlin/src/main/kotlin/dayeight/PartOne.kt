package dev.mfazio.aoc.twentyone.dayeight

import dev.mfazio.aoc.twentyone.util.InputHelpers
import dev.mfazio.aoc.twentyone.util.printEach
import kotlin.math.abs

fun sevenSegmentSearch(input: List<String>): Int =
    getInputPairs(input).map { it.second }.sumOf { digits -> digits.count { digit -> digit.length <= 4 || digit.length == 7 } }

fun getInputPairs(input: List<String>) = input.map { line ->
    val (digits, fourDigit) = line.split(" | ")

    digits.split(" ") to fourDigit.split(" ")
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayeight.txt")

    val result = sevenSegmentSearch(input)

    println(result)

}