package dev.mfazio.aoc.twentyone.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun diagnosticRatings(input: List<String>): Int {

    var oxygenNumbers = input.toList()

    var oxygenInd = 0

    while (oxygenNumbers.size > 1) {
        val digitCounts = (0 until (oxygenNumbers.firstOrNull()?.length ?: 0)).map { index ->
            oxygenNumbers.count { it[index] == '1' }
        }

        oxygenNumbers = oxygenNumbers.filter { oxyNumber ->
            val bit = if (digitCounts[oxygenInd].toDouble() >= (oxygenNumbers.size.toDouble() / 2.0)) '1' else '0'

            oxyNumber[oxygenInd] == bit
        }
        oxygenInd++
    }

    println(oxygenNumbers.firstOrNull()?.toInt(2))

    var scrubberNumbers = input.toList()

    var scrubberInd = 0

    while (scrubberNumbers.size > 1) {
        val digitCounts = (0 until (scrubberNumbers.firstOrNull()?.length ?: 0)).map { index ->
            scrubberNumbers.count { it[index] == '1' }
        }

        scrubberNumbers = scrubberNumbers.filter { scrubberNum ->
            val bit = if (digitCounts[scrubberInd].toDouble() >= (scrubberNumbers.size.toDouble() / 2.0)) '0' else '1'

            scrubberNum[scrubberInd] == bit
        }
        scrubberInd++
    }

    println(scrubberNumbers.firstOrNull()?.toInt(2))



    return( oxygenNumbers.firstOrNull()?.toInt(2) ?: 0) * (scrubberNumbers.firstOrNull()?.toInt(2) ?: 0)
}

fun main() {
    val input = getResourceAsListOfStrings("/daythree.txt")

    val result = diagnosticRatings(input)

    println(result)
}