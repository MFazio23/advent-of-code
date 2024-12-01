package dev.mfazio.aoc.twentyfour.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOneQuick(
                getResourceAsListOfStrings("day-one.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val (first, second) = getLocationLists(input)

    return first.zip(second) { a, b -> abs(a - b) }.sum()
}

fun getLocationLists(input: List<String>): Pair<List<Int>, List<Int>> {
    val firstList = mutableListOf<Int>()
    val secondList = mutableListOf<Int>()
    input.forEach { line ->
        val (first, second) = line.split(Regex("\\s+")).map { it.toInt() }
        firstList.add(first)
        secondList.add(second)
    }

    return firstList.sorted() to secondList.sorted()
}

fun partOneQuick(input: List<String>): Int {
    val firstList = mutableListOf<Int>()
    val secondList = mutableListOf<Int>()
    input.forEach { line ->
        val (first, second) = line.split("   ").map { it.toInt() }
        firstList.add(first)
        secondList.add(second)
    }

    firstList.sort()
    secondList.sort()

    return firstList.mapIndexed { index, first ->
        abs(secondList[index] - first)
    }.sum()
}