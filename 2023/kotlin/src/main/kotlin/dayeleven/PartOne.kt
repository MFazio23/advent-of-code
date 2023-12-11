package dev.mfazio.aoc.twentythree.dayeleven

import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-eleven.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {

    val extraLine = (0 until input.first().length).joinToString("") { "." }

    val extraLines = mutableListOf<Int>()
    val extraColumns = mutableListOf<Int>()
    input.forEachIndexed { index, line ->
        if (line.split("").filter { it.isNotBlank() }.all { it == "." }) {
            extraLines.add(index + extraLines.size)
        }
    }

    (0 until input.first().length).forEach { col ->
        input.all { it[col] == '.' }.also { if (it) extraColumns.add(col) }
    }

    val withLines = input.toMutableList().apply {
        extraLines.forEach { add(it, extraLine) }
    }.toList()

    val withColumns = withLines.mapIndexed { _, line ->
        line.mapIndexed { col, s ->
            if (extraColumns.contains(col)) {
                "$s."
            } else {
                s
            }
        }.joinToString("")
    }

    var valueInd = 1

    val points = withColumns.mapIndexed { row, line ->
        line.mapIndexed { col, s ->
            val value = if (s == '#') {
                valueInd++
            } else 0
            Point(value, col, row)
        }
    }.flatten()

    val galaxies = points.filter { it.data > 0 }

    val pairs = galaxies.flatMap { galaxy ->
        galaxies.filter { it != galaxy }.map { other ->
            listOf(galaxy, other).sortedBy { it.data }
        }
    }.distinct()

    return pairs.sumOf { (a, b) -> a.distanceTo(b) }
}

fun <T> Point<T>.distanceTo(other: Point<T>): Int =
    abs(this.x - other.x) + abs(this.y - other.y)