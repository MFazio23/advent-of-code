@file:Suppress("DuplicatedCode")

package dev.mfazio.aoc.twentytwo.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-three.txt")

    println(goodPartOne(input))
}
fun funPartOne(input: List<String>): Int {

    val itemType = input.map { compartment ->
        val first = compartment.take(compartment.length / 2)
        val last = compartment.takeLast(compartment.length / 2)

        first.find { last.contains(it) }
    }

    val groups = input.chunked(3).map { (a, b, c) ->
        a.find { b.contains(it) && c.contains(it) }
    }

    val itemTypeTotal = itemType.filterNotNull().sumOf { type ->
        if ("[A-Z]".toRegex().matches(type.toString())) {
            type.code - 38
        } else {
            type.code - 96
        }
    }

    val groupsTotal = groups.filterNotNull().sumOf { type ->
        if ("[A-Z]".toRegex().matches(type.toString())) {
            type.code - 38
        } else {
            type.code - 96
        }
    }

    return itemTypeTotal
}

fun goodPartOne(input: List<String>): Int {
    val itemType = input.map { compartment ->
        val first = compartment.take(compartment.length / 2)
        val last = compartment.takeLast(compartment.length / 2)

        first.find { last.contains(it) }
    }

    return itemType.filterNotNull().sumOf { type ->
        if ("[A-Z]".toRegex().matches(type.toString())) {
            type.code - 38
        } else {
            type.code - 96
        }
    }
}