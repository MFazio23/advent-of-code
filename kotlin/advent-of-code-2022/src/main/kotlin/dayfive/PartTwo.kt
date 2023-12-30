package dev.mfazio.aoc.twentytwo.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("/day-five.txt")

    println(goodPartTwo(input))
}

fun goodPartTwo(input: List<String>): String {
    val (stacks, instructions) = getStartingState(input)

    instructions.forEach { instruction ->
        val (count, from, to) = instruction

        val blocks = (1..count).map {
            stacks[from]?.pop()
        }

        stacks[to]?.addAll(blocks.reversed())
    }

    return stacks.values.joinToString("") {
        it.peek()
    }.replace("[", "").replace("]", "")
}