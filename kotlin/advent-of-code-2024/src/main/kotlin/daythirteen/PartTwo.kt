package dev.mfazio.aoc.twentyfour.daythirteen

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-thirteen.txt",
    ) {
        partTwo(it)
    }
}

const val PRIZE_OFFSET = 10_000_000_000_000L

fun partTwo(input: List<String>): Long {
    val instructions = getInstructions(input).map { instruction ->
        val (buttonA, buttonB, prize) = instruction

        LongClawInstruction(
            buttonA = ClawPoint(buttonA.x.toLong(), buttonA.y.toLong()),
            buttonB = ClawPoint(buttonB.x.toLong(), buttonB.y.toLong()),
            prize = ClawPoint(prize.x + PRIZE_OFFSET, prize.y + PRIZE_OFFSET)
        )
    }

    return instructions.sumOf { instruction ->
        val (buttonA, buttonB, prize) = instruction
        val a = (prize.x * buttonB.y - prize.y * buttonB.x) / (buttonA.x * buttonB.y - buttonA.y * buttonB.x)
        val b = (buttonA.x * prize.y - buttonA.y * prize.x) / (buttonA.x * buttonB.y - buttonA.y * buttonB.x)

        val validX = buttonA.x * a + buttonB.x * b == prize.x
        val validY = buttonA.y * a + buttonB.y * b == prize.y

        if (validX && validY) {
            (a * 3) + b
        } else 0
    }
}

data class LongClawInstruction(
    val buttonA: ClawPoint,
    val buttonB: ClawPoint,
    val prize: ClawPoint
)

data class ClawPoint(
    val x: Long,
    val y: Long
)