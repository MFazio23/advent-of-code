package dev.mfazio.aoc.twentythree.dayeight

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {

    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-eight.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Long {
    val directions = input.first().split("").filter { it.isNotEmpty() }
    val instructions = parseInstructions(input)

    var instructionCount = 0L
    var instructionNumber = 0L

    var instruction: Pair<String, String>? = instructions["AAA"]

    while(true) {
        if (directions[instructionNumber.toInt()] == "L") {
            instruction?.first
        } else {
            instruction?.second
        }.let {
            instructionCount++
            instructionNumber++
            if (instructionNumber >= directions.size) instructionNumber = 0
            if (it == "ZZZ") return instructionCount
            instruction = instructions[it]
        }
    }
}

fun parseInstructions(input: List<String>): Map<String, Pair<String, String>> = input.drop(2).associate { line ->
    val (start, others) = line.split(" = ")
    val (left, right) = others.replace("(", "").replace(")", "").split(", ")

    start to (left to right)
}
