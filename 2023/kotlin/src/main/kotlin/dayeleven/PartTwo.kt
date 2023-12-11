package dev.mfazio.aoc.twentythree.dayeleven

import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.max
import kotlin.math.min
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            totalPathDistanceWithExpandedGalaxies(
                getResourceAsListOfStrings("day-eleven.txt"),
                1_000_000
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}


fun partTwo(input: List<String>, factor: Int): Long {
    val extraLines = mutableListOf<Int>()
    val extraColumns = mutableListOf<Int>()

    input.forEachIndexed { index, line ->
        if (line.split("").filter { it.isNotBlank() }.all { it == "." }) {
            extraLines.add(index)
        }
    }

    (0 until input.first().length).forEach { col ->
        input.all { it[col] == '.' }.also {
            if (it) {
                extraColumns.add(col)
            }
        }
    }

    var valueInd = 1

    val points = input.mapIndexed { row, line ->
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

    val newDistances = pairs.associateWith { (a, b) ->
        (a.distanceTo(b) +
        crossedColumns(a, b, extraColumns) * (factor - 1) +
        crossedRows(a, b, extraLines) * (factor - 1)).toLong()
    }

    return newDistances.values.sum()
}

fun <T> crossedColumns(a: Point<T>, b: Point<T>, extraColumns: List<Int>): Int {
    val xRange = (min(a.x, b.x) .. max(a.x, b.x))
    return extraColumns.count { it in xRange }
}
fun <T> crossedRows(a: Point<T>, b: Point<T>, extraLines: List<Int>): Int {
    val yRange = (min(a.y, b.y) .. max(a.y, b.y))
    return extraLines.count { it in yRange }
}
fun printGalaxyWithExtras(points: List<Point<Int>>, extraLines: List<Int>, extraColumns: List<Int>) {
    points.groupBy { it.y }.forEach { (_, row) ->
        row.forEach {
            print(
                when {
                    it.data > 0 -> it.data
                    extraLines.contains(it.y) ||
                            extraColumns.contains(it.x) -> "*"

                    else -> "."
                }
            )
        }
        println()
    }
}