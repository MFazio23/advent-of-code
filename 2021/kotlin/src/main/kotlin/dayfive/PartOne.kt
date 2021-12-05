package dev.mfazio.aoc.twentyone.dayfive

import dev.mfazio.aoc.twentyone.util.InputHelpers
import kotlin.math.abs

fun hydrothermalVenture(input: List<String>): Int {

    val vents = input.map { HydrothermalVent.convertStringToVent(it) }
    val points = vents.mapNotNull { vent -> vent?.getVentLines() }
    return points
        .flatten()
        .groupingBy { it }
        .eachCount()
        .count { it.value > 1 }
}

data class HydrothermalVent(
    val x1: Int,
    val y1: Int,
    val x2: Int,
    val y2: Int,
) {
    fun getVentLines() = when {
        x1 == x2 -> {
            val (low, high) = if (y1 > y2) y2 to y1 else y1 to y2

            (low..high).map { y -> x1 to y }
        }
        y1 == y2 -> {
            val (low, high) = if (x1 > x2) x2 to x1 else x1 to x2

            (low..high).map { x -> x to y1 }
        }
        else -> emptyList()
    }

    fun getVentLinesPlusDiagonal() = when {
        abs(x1 - x2) == abs(y1 - y2) -> {
            val xPoints = if (x1 > x2) (x1 downTo x2) else (x1..x2)
            val yPoints = (if (y1 > y2) (y1 downTo y2) else (y1..y2)).toList()

            xPoints.mapIndexed { ind, x ->
                x to yPoints[ind]
            }
        }

        else -> getVentLines()
    }

    override fun toString() = "($x1, $y1) -> ($x2, $y2)"

    companion object {
        private val ventStringRegex = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex()
        fun convertStringToVent(lineString: String): HydrothermalVent? =
            ventStringRegex.find(lineString)?.groupValues?.let { values ->
                HydrothermalVent(
                    values[1].toInt(),
                    values[2].toInt(),
                    values[3].toInt(),
                    values[4].toInt(),
                )
            }
    }
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayfive.txt")

    val result = hydrothermalVenture(input)

    println(result)
}