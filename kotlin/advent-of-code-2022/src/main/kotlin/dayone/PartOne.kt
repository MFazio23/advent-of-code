package dev.mfazio.aoc.twentytwo.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-one.txt")

    println(getCalorieTotals(input).max())
}

/**
 * After getting the answer, I tried to come up with a nicer way to handle the problem.
 */
fun getCalorieTotals(input: List<String>): List<Int> {
    val groups = input.fold(mutableListOf<MutableList<String>>(mutableListOf())) { list, item ->
        list.apply {
            if (item.isBlank()) {
                add(mutableListOf())
            } else {
                last().add(item)
            }
        }
    }

    return groups.map { list -> list.sumOf { it.toInt() } }
}

/**
 * This was the version I did quickly to get an answer out there.
 * The version above is a better solution.
 */
fun getCalorieTotalsQuickly(input: List<String>): Int {
    var total = 0
    val cals = mutableListOf<Int>()
    input.forEach {
        if (it.isBlank()) {
            cals.add(total)
            total = 0
        } else {
            total += it.toInt()
        }
    }

    println("Most calories: ${cals.max()}")
    println("Three most calories: ${cals.sortedByDescending { it }.take(3).sum()}")

    return cals.sortedByDescending { it }.take(3).sum()
}