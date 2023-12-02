package dev.mfazio.aoc.twentytwo.daytwentyfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.math.pow
import kotlin.math.roundToInt

fun main() {
    val input = getResourceAsListOfStrings("day-twentyfive.txt")
    println(funPartOne(input))
}

fun funPartOne(input: List<String>): Int {
    val decimals = input.map { line ->
        line.reversed().mapIndexed { index, c ->
            5.0.pow(index) * when(c) {
                '=' -> -2
                '-' -> -1
                else -> c.digitToInt()
            }
        }.sum()
    }
    val total = decimals.sum().roundToInt()


    decimals.printEach()
    return -1
}