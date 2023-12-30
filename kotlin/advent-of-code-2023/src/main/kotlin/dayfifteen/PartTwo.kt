package dev.mfazio.aoc.twentythree.dayfifteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-fifteen.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val steps = input.first().split(",").map(Step.Companion::fromText)

    val boxes = mutableMapOf<Int, MutableList<Step>>().withDefault { mutableListOf() }

    steps.forEach { step ->
        val hash = step.code.hash()
        val box = boxes[hash] ?: mutableListOf()

        val existingIndex = box.indexOfFirst { it.code == step.code }

        if (existingIndex >= 0) {
            if (step.action == Step.StepAction.Add) {
                box[existingIndex] = step
            } else {
                box.removeAt(existingIndex)
            }
        } else if (step.action == Step.StepAction.Add) {
            box.add(step)
        }

        boxes[hash] = box
    }

    return boxes.flatMap { (boxNum, boxList) ->
        boxList.mapIndexed { index, box ->
            (boxNum + 1) * (index + 1) * (box.focalLength ?: 1)
        }
    }.sum()
}

data class Step(
    val code: String,
    val action: StepAction,
    val focalLength: Int? = null,
) {
    enum class StepAction { Remove, Add }

    companion object {
        private val codeRegex = "(\\w+)([=-])(\\d)?".toRegex()
        fun fromText(text: String): Step {
            val (_, code, symbol, focalLength) = codeRegex.findAll(text).map { match ->
                match.groupValues
            }.toList().flatten()

            return Step(
                code,
                if (symbol == "-") StepAction.Remove else StepAction.Add,
                focalLength.toIntOrNull()
            )
        }
    }
}