package dev.mfazio.aoc.twenty.dayone

import dev.mfazio.aoc.twenty.util.InputHelpers

fun reportRepair(reportItems: List<Int>, expectedTotal: Int): Int = reportItems.map { item ->
    val secondItem = reportItems.firstOrNull { secondItem ->
        item + secondItem == expectedTotal
    } ?: 0

    item * secondItem
}.firstOrNull { it != 0 } ?: 0

fun main() {
    val inputs =
        InputHelpers.getListOfStringsFromFile("/dayone.txt").map(String::toInt)

    println("Result = ${reportRepair(inputs, 2020)}")
}