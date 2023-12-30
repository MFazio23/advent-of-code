package dev.mfazio.aoc.twenty.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

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
    val inputs = getResourceAsListOfStrings("/dayone.txt").map(String::toInt)

    println("Result = ${reportRepairTriple(inputs, 2020)}")
}