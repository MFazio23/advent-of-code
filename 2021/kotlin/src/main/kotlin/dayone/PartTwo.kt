package dev.mfazio.aoc.twentyone.dayone

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun reportRepairTriple(reportItems: List<Int>, expectedTotal: Int): Int = reportItems.flatMap { first ->
    reportItems.flatMap { second ->
        reportItems.map { third ->
            if (first + second + third == expectedTotal) {
                first * second * third
            } else 0
        }
    }
}.firstOrNull { it != 0 } ?: 0

fun main() {
    val inputs =
        InputHelpers.getListOfStringsFromFile("/dayone.txt").map(String::toInt)

    println("Result = ${reportRepairTriple(inputs, 2020)}")
}