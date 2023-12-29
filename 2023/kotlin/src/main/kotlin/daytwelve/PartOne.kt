package dev.mfazio.aoc.twentythree.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-twelve.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

val damageRegex = "[#?]+".toRegex()

fun partOne(input: List<String>): Int {
    val conditionRecords = input.map { line ->
        val (recordsLine, damageLengthString) = line.split(" ")
        val damageLengths = damageLengthString.split(",").filter { it.isNotBlank() }.map { it.toInt() }

        recordsLine to damageLengths
    }

    return conditionRecords.sumOf { (recordsLine, damageLengths) ->
        getChoicesForLine(recordsLine, damageLengths)
    }
}

fun getChoicesForLine(recordsLine: String, damageLengths: List<Int>): Int {

    val variations = mutableListOf<List<String>>()

    recordsLine.forEach {
        if (it == '?') {
            if (variations.isEmpty()) {
                variations.addAll(
                    listOf(
                        listOf("#"),
                        listOf(".")
                    )
                )
            } else {
                val newVariations =
                    variations.map { variation ->
                        listOf(
                            variation + "#",
                            variation + "."
                        )
                    }.flatten().toList()
                variations.clear()
                variations.addAll(newVariations)
            }
        } else {
            if (variations.isEmpty()) {
                variations.addAll(
                    listOf(
                        listOf(it.toString())
                    )
                )
            } else {
                val newVariations =
                    variations.map { variation ->
                        listOf(
                            variation + it.toString(),
                        )
                    }.flatten().toList()
                variations.clear()
                variations.addAll(newVariations)
            }
        }
    }

    val newVariations = variations.map { variation -> variation.joinToString("") }

    val damageGroups = newVariations.map { variation ->
        damageRegex.findAll(variation).map { matchResult ->
            matchResult.value.length
        }.toList()
    }

    return damageGroups.count { damageLengths == it }
}