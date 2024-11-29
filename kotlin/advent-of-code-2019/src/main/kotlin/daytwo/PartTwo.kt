package dev.mfazio.aoc.nineteen.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-two.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val startingProgram = input.first().split(",").map { it.toInt() }

    val pairs = listOf(
        1 to 0,
        1 to 2,
        2 to 2,
        3 to 2,
        4 to 2,
        5 to 2,
        6 to 9,
        40 to 19,
    )

    /*
    OK, here's the deal.
    I ran through the pairs here, figured out the pattern, then threw it into a spreadsheet
    to see when it matched the number I wanted. It was way easier that way.
     */

    pairs.forEach { (noun, verb) ->
        val program = startingProgram.toMutableList()
        program[1] = noun
        program[2] = verb
        for (ind in 0 until program.size - 3 step 4) {
            val opcode = program[ind]
            val inputA = program[ind + 1]
            val inputB = program[ind + 2]
            val output = program[ind + 3]

            val result = when (opcode) {
                1 -> program[inputA] + program[inputB]
                2 -> program[inputA] * program[inputB]
                99 -> {
                    break
                }

                else -> {
                    throw Exception("Invalid opcode: $opcode")
                }
            }

            program[output] = result
        }

        println("$noun\t$verb\t${program[0]}")
    }

    return -1
}
