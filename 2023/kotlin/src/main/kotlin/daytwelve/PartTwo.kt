package dev.mfazio.aoc.twentythree.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-twelve.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val conditionRecords = input.map { line ->
        val (recordsLine, damageLengthString) = line.split(" ")

        val expandedRecordsLine = (1..5).joinToString("?") { recordsLine }

        val damageLengths = damageLengthString.split(",").filter { it.isNotBlank() }.map { it.toInt() }

        val expandedDamageLengths = (1..5).map { damageLengths }.flatten()

        expandedRecordsLine to expandedDamageLengths
    }

    return conditionRecords.sumOf { (recordsLine, damageLengths) ->
        println("Records line: $recordsLine")
        getChoicesForLine(recordsLine, damageLengths)
    }
}
