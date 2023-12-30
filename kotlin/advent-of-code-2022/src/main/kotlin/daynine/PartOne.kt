package dev.mfazio.aoc.twentytwo.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.abs

fun main() {
    val input = getResourceAsListOfStrings("day-nine.txt")
    println(funPartOne(input))
}

fun funPartOne(input: List<String>): Int {
    var headRow = 0
    var headColumn = 0

    var tailRow = 0
    var tailColumn = 0

    val locations = mutableSetOf(tailRow to tailColumn)

    input.forEach { line ->
        val (direction, distance) = line.split(" ")

        repeat(distance.toInt()) {
            when(direction) {
                "R" -> headColumn += 1
                "L" -> headColumn -= 1
                "U" -> headRow -= 1
                "D" -> headRow += 1
            }

            when {
                headRow == tailRow && headColumn == tailColumn + 2 -> tailColumn += 1
                headRow == tailRow && headColumn == tailColumn - 2 -> tailColumn -= 1
                headColumn == tailColumn && headRow == tailRow + 2 -> tailRow += 1
                headColumn == tailColumn && headRow == tailRow - 2 -> tailRow -= 1
                (abs(headRow - tailRow) + abs(headColumn - tailColumn)) > 2 -> {
                    tailRow += headRow.compareTo(tailRow)
                    tailColumn += headColumn.compareTo(tailColumn)
                }
            }

            println("Head: $headRow x $headColumn")
            println("Tail: $tailRow x $tailColumn")
            println("RowC: ${headRow.compareTo(tailRow)}")
            println("ColC: ${headColumn.compareTo(tailColumn)}")

            locations.add(tailRow to tailColumn)
        }
    }

    println(locations)

    return locations.size
}