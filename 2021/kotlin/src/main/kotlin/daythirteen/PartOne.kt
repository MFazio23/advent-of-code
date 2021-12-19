package dev.mfazio.aoc.twentyone.daythirteen

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun getVisibleDotCount(input: List<String>, instructionsToRun: Int? = null): Int = getVisibleDots(input, instructionsToRun).distinct().size

fun getVisibleDots(input: List<String>, instructionsToRun: Int? = null): List<Point> {
    val blankIndex = input.indexOf("")

    val pointStrings = input.slice(0 until blankIndex)
    val instructionStrings = input.slice(blankIndex + 1 until input.size)

    val points = pointStrings.map { it.split(",") }.map { (x, y) -> Point(x.toInt(), y.toInt()) }.toSet()
    val instructions = instructionStrings.map { instructionString -> FoldAction.fromString(instructionString) }.let { instructionList ->
        if (instructionsToRun != null) {
            instructionList.take(instructionsToRun)
        } else instructionList
    }

    return instructions.fold(points) { currentPoints, instruction ->
        currentPoints.mapNotNull { point ->
            when {
                instruction.axis == FoldAxis.Y && instruction.line < point.y -> {
                    val newPoint = Point(
                        x = point.x,
                        y = (instruction.line * 2) - point.y
                    )
                    if (currentPoints.contains(newPoint)) null else newPoint
                }
                instruction.axis == FoldAxis.X && instruction.line < point.x -> {
                    val newPoint = Point(
                        x = (instruction.line * 2) - point.x,
                        y = point.y
                    )
                    if (currentPoints.contains(newPoint)) null else newPoint
                }
                else -> point
            }
        }.toSet()
    }.toList()
}

data class Point(
    val x: Int,
    val y: Int,
)

data class FoldAction(
    val axis: FoldAxis,
    val line: Int,
) {
    companion object {
        private const val foldAlongString = "fold along "
        fun fromString(input: String) =
            input.replace(foldAlongString, "").split("=").let { (axisString, lineString) ->
                FoldAction(
                    axis = if (axisString == "y") FoldAxis.Y else FoldAxis.X,
                    line = lineString.toInt()
                )
            }
    }
}

enum class FoldAxis { X, Y }

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/daythirteen.txt")

    val result = getVisibleDotCount(input)

    println(result)
}