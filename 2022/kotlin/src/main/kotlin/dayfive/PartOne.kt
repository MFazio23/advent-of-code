package dev.mfazio.aoc.twentytwo.dayfive

import dev.mfazio.aoc.twentytwo.util.InputHelpers
import java.util.*

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/day-five.txt")

    println(goodPartOne(input))
}

fun funPartOne(input: List<String>): Int {
    val stackLines = mutableListOf<String>()
    val stacks = mutableMapOf<Int, Stack<String>>()
    val instructions = mutableListOf<String>()

    var ontoInstructions = false

    input.forEach { line ->
        if (line.isBlank()) {
            ontoInstructions = true
        } else if (!ontoInstructions) {
            if (line.startsWith("[")) {
                stackLines.add(line)
            }
        } else {
            instructions.add(line)
        }
    }

    stackLines.reversed().forEach { line ->
        line.windowed(3, 4, true).let {
            it.forEachIndexed { index, s ->
                if (s.isNotBlank()) {
                    stacks[index + 1]?.add(s) ?: run {
                        stacks[index + 1] = Stack<String>().apply { add(s) }
                    }
                }
            }
        }
    }

    val instr = instructions.mapNotNull { line ->
        val result = "move (\\d+) from (\\d+) to (\\d+)".toRegex().matchEntire(line)

        result?.groupValues?.mapNotNull { it.toIntOrNull() }
    }

    /**
     * Part one
     */
    /*instr.forEach { instruction ->
        val (count, from, to) = instruction

        repeat(count) {
            val out = stacks[from]?.pop()
            stacks[to]?.add(out)
        }
    }*/

    /**
     * Part two
     */
    instr.forEach { instruction ->
        val (count, from, to) = instruction

        val blocks = (1..count).map {
            stacks[from]?.pop()
        }

        stacks[to]?.addAll(blocks.reversed())
    }

    val endResult = stacks.values.map {
        it.peek()
    }

    println(endResult.joinToString("").replace("[", "").replace("]", ""))

    return -1
}

/**
 * This is after taking some time to think through things.
 */

val instructionRegex = "move (\\d+) from (\\d+) to (\\d+)".toRegex()

data class SupplyStacks(
    val stacks: Map<Int, Stack<String>>,
    val instructions: List<InstructionSet>,
)

data class InstructionSet(
    val count: Int,
    val start: Int,
    val end: Int,
)

fun getStartingState(input: List<String>): SupplyStacks {
    val stackLines = mutableListOf<String>()
    val stacks = mutableMapOf<Int, Stack<String>>()
    val instructions = mutableListOf<InstructionSet>()

    var ontoInstructions = false

    input.forEach { line ->
        if (line.isBlank()) {
            ontoInstructions = true
        } else if (!ontoInstructions) {
            if (line.startsWith("[")) {
                stackLines.add(line)
            }
        } else {
            instructionRegex.matchEntire(line)?.groupValues?.let { groupValues ->
                instructions.add(
                    InstructionSet(
                        count = groupValues[1].toInt(),
                        start = groupValues[2].toInt(),
                        end = groupValues[3].toInt(),
                    )
                )
            }
        }
    }

    stackLines.reversed().forEach { line ->
        line.windowed(3, 4, true).let {
            it.forEachIndexed { index, s ->
                if (s.isNotBlank()) {
                    stacks[index + 1]?.add(s) ?: run {
                        stacks[index + 1] = Stack<String>().apply { add(s) }
                    }
                }
            }
        }
    }

    return SupplyStacks(
        stacks = stacks,
        instructions = instructions
    )
}

fun goodPartOne(input: List<String>): String {
    val (stacks, instructions) = getStartingState(input)

    instructions.forEach { instruction ->
        val (count, from, to) = instruction

        repeat(count) {
            val out = stacks[from]?.pop()
            stacks[to]?.add(out)
        }
    }

    return stacks.values.joinToString("") {
        it.peek()
    }.replace("[", "").replace("]", "")
}