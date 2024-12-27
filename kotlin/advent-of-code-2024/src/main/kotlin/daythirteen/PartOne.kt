package dev.mfazio.aoc.twentyfour.daythirteen

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import kotlin.math.ceil
import kotlin.math.floor

suspend fun main() {
    runPuzzle(
        inputFileName = "day-thirteen.txt",
    ) {
        partOne(it)
    }
}

const val MAX_BUTTON_PRESSES = 100

fun partOne(input: List<String>): Int {
    val instructions = getInstructions(input)

    val instructionResults = instructions.mapNotNull { instruction ->
        getBestButtonCombo(instruction)
    }

    return instructionResults.sum()
}

fun partOneWithMath(input: List<String>): Int {
    val instructions = getInstructions(input)

    val instructionResults = instructions.map { instruction ->
        val (buttonA, buttonB, prize) = instruction
        val a = (prize.x * buttonB.y - prize.y * buttonB.x) / (buttonA.x * buttonB.y - buttonA.y * buttonB.x)
        val b = (buttonA.x * prize.y - buttonA.y * prize.x) / (buttonA.x * buttonB.y - buttonA.y * buttonB.x)

        val validX = buttonA.x * a + buttonB.x * b == prize.x
        val validY = buttonA.y * a + buttonB.y * b == prize.y

        if (validX && validY) {
            (a * 3) + b
        } else 0
    }

    return instructionResults.sum()
}

fun getInstructions(input: List<String>): List<ClawInstruction> {
    val instructionLines = input.chunked(4)

    return instructionLines.map { lines -> ClawInstruction.fromInstructionLines(lines) }
}

fun getBestButtonCombo(instruction: ClawInstruction): Int? {
    val buttonA = instruction.buttonA
    val buttonB = instruction.buttonB
    val prize = instruction.prize

    val (minX, maxX) = getButtonMinMax(buttonA.x, buttonB.x, prize.x)
    val (minY, maxY) = getButtonMinMax(buttonA.y, buttonB.y, prize.y)

    val invalidScenarios = listOf(
        minX > MAX_BUTTON_PRESSES,
        minY > MAX_BUTTON_PRESSES,
        maxX < 0,
        maxY < 0,
    )

    if (invalidScenarios.any { it }) {
        return null
    }

    val validCombinations = getValidCombinations(
        instruction = instruction,
        minX = if (minX < 0) 0 else minX,
        maxX = if (maxX > MAX_BUTTON_PRESSES) MAX_BUTTON_PRESSES else maxX,
    )

    val costs = validCombinations.map { (a, b) ->
        a * 3 + b
    }

    return costs.minOrNull()
}

fun getValidCombinations(
    instruction: ClawInstruction,
    minX: Int,
    maxX: Int,
): List<Pair<Int, Int>> {
    val (buttonA, buttonB, prize) = instruction
    val validAList = (minX..maxX).mapNotNull { a ->
        if ((prize.x - (a * buttonA.x)) % buttonB.x == 0) {
            a to (prize.x - (a * buttonA.x)) / buttonB.x
        } else {
            null
        }
    }

    val validCombinations = validAList.filter { (a, b) ->
        prize.y == (a * buttonA.y) + (b * buttonB.y)
    }

    return validCombinations
}


fun getButtonMinMax(a: Int, b: Int, p: Int): Pair<Int, Int> {
    val aMin = floor((p - (b * 100)) / a.toDouble()).toInt()
    val aMax = ceil(p / a.toDouble()).toInt()

    return Pair(aMin, aMax)
}

data class ClawInstruction(
    val buttonA: Point<String>,
    val buttonB: Point<String>,
    val prize: Point<String>,
) {
    companion object {
        const val PRIZE_DATA_VALUE = "P"

        private val buttonRegex = Regex("Button (\\S): X\\+(\\d+), Y\\+(\\d+)")
        private val prizeRegex = Regex("Prize: X=(\\d+), Y=(\\d+)")

        fun fromInstructionLines(instructionLines: List<String>): ClawInstruction {
            val buttonA = getButtonFromText(instructionLines[0])
            val buttonB = getButtonFromText(instructionLines[1])
            val prize = getPrizeFromText(instructionLines[2])

            return ClawInstruction(buttonA, buttonB, prize)
        }

        private fun getButtonFromText(line: String): Point<String> {
            val match = buttonRegex.matchEntire(line) ?: throw IllegalArgumentException("Invalid button line: $line")
            val (button, x, y) = match.destructured

            return Point(button, x.toInt(), y.toInt())
        }

        private fun getPrizeFromText(line: String): Point<String> {
            val match = prizeRegex.matchEntire(line) ?: throw IllegalArgumentException("Invalid prize line: $line")
            val (x, y) = match.destructured

            return Point(PRIZE_DATA_VALUE, x.toInt(), y.toInt())
        }
    }
}