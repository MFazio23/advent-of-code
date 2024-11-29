package dev.mfazio.aoc.nineteen.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-two.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val program = input.first().split(",").map { it.toInt() }.toMutableList()
    program[1] = 12
    program[2] = 2
    println("Starting program")
    println(program)
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

    println("Ending program")
    println(program)

    println()

    return program[0]
}
