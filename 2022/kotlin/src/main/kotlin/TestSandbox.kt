package dev.mfazio.aoc.twentytwo

import dev.mfazio.utils.extensions.printEach
import kotlin.math.roundToInt

// This is for trying random stuff out quickly. It's effectively a scratch file.
// Also, it keeps my package names in line.

fun main() {
    val values = listOf(3, 4, 6, 10)

    val total = values.sum()

    val reduceTotal = values.reduce { acc, i -> acc + i }

    println("Total=$total")
    println("RTotal=$reduceTotal")
}