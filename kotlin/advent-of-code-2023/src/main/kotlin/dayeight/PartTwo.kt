package dev.mfazio.aoc.twentythree.dayeight

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.math.findLCM
import kotlin.system.measureTimeMillis

fun main() {

    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-eight.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Long {
    val directions = input.first().split("").filter { it.isNotEmpty() }
    val instructions = parseInstructions(input).map { (id, directions) ->
        Node(id, directions.first, directions.second)
    }

    println(instructions)

    var instructionCount = 0L
    var instructionNumber = 0L

    var currentInstructions: List<Node> = instructions.filter { it.id.endsWith("A") }

    var runCount = 0L

    val countMap = List(currentInstructions.size) { index -> index to 0L }.toMap().toMutableMap()

    println(countMap)

    while (true) {
        val isLeft = directions[instructionNumber.toInt()] == "L"

        currentInstructions.map { node ->
            if (isLeft) {
                instructions.find { it.id == node.left }
            } else {
                instructions.find { it.id == node.right }
            }
        }.let { nextInstructions ->
            instructionCount++
            instructionNumber++
            if (instructionNumber >= directions.size) {
                instructionNumber = 0
            }
            if (nextInstructions.all { it?.id?.endsWith("Z") == true }) return instructionCount.toLong()
            currentInstructions = nextInstructions.filterNotNull()
            currentInstructions.forEachIndexed { index, node ->
                if (node.id.endsWith("Z") && countMap[index] == 0L) {
                    countMap[index] = instructionCount
                }
            }
            if (countMap.values.all { it > 0 }) {
                return countMap.values.map { it.toLong() }.findLCM()
            }
        }
        runCount++
    }
}

data class Node(
    val id: String,
    val left: String,
    val right: String,
)