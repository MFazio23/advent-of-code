package dev.mfazio.aoc.twentytwo.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.math.abs

fun main() {
    val input = getResourceAsListOfStrings("day-nine.txt")
    println(funPartTwo(input))
}

fun funPartTwo(input: List<String>): Int {

    val knots = (0..9).map { Location(it, 0, 0) }.toMutableList()

    val locations = mutableSetOf(knots.last())

    val head = knots.first()

    input.forEach { line ->
        val (direction, distance) = line.split(" ")

        repeat(distance.toInt()) {
            when (direction) {
                "R" -> head.col += 1
                "L" -> head.col -= 1
                "U" -> head.row -= 1
                "D" -> head.row += 1
            }

            for (k in 0 until knots.size) {
                val lead = knots[k]
                val tail = knots.getOrNull(k + 1)

                if (tail != null) {
                    when {
                        lead.row == tail.row && lead.col == tail.col + 2 -> tail.col += 1
                        lead.row == tail.row && lead.col == tail.col - 2 -> tail.col -= 1
                        lead.col == tail.col && lead.row == tail.row + 2 -> tail.row += 1
                        lead.col == tail.col && lead.row == tail.row - 2 -> tail.row -= 1
                        (abs(lead.row - tail.row) + abs(lead.col - tail.col)) > 2 -> {
                            tail.row += lead.row.compareTo(tail.row)
                            tail.col += lead.col.compareTo(tail.col)
                        }
                    }

                    if (tail.knotNum == 9) {
                        locations.add(tail.copy())
                    }
                }
            }
        }

        println(knots)
    }

    locations.printEach()

    return locations.size
}

data class Location(
    val knotNum: Int,
    var row: Int,
    var col: Int,
)