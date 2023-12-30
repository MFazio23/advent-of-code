package dev.mfazio.aoc.eighteen.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.isNotNullOrEmpty


fun main () {
    println(
        partTwo(
            getResourceAsListOfStrings("day-one.txt")
        )
    )
}

fun partTwo(input: List<String>): Int {
    val intInput = input.filter{ it.isNotNullOrEmpty() }.map { it.toInt() }
    var total = 0
    val inputs = mutableListOf<Int>()

    while(true) {
        intInput.forEach {
            total += it
            if (inputs.contains(total)) return total
            else inputs.add(total)
        }
    }
}