package dev.mfazio.aoc.twentythree.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings


fun main() {
    println(
        partTwo(
            getResourceAsListOfStrings("day-one.txt")
        )
    )
}

val numberMap = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun partTwo(input: List<String>) = input.sumOf { line ->
    line.windowed(5, 1, true).mapNotNull { window ->
        window.firstOrNull()?.let { firstCharacter ->
            if (firstCharacter.isDigit()) {
                firstCharacter.digitToInt()
            } else {
                numberMap.filter { (word, number) -> window.startsWith(word, true) }.values.firstOrNull()
            }
        }
    }.let { result -> "${result.first()}${result.last()}".toInt() }
}

val inputs = mapOf(
    "1" to 1,
    "2" to 2,
    "3" to 3,
    "4" to 4,
    "5" to 5,
    "6" to 6,
    "7" to 7,
    "8" to 8,
    "9" to 9,
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun partTwoQuick(input: List<String>): Int {
    return input.sumOf { line ->
        val lineResult = arrayOfNulls<Int?>(line.length).toMutableList()
        inputs.forEach { (word, number) ->
            val index = line.indexOf(word)
            if (index >= 0) {
                lineResult[index] = number
            }
            val index2 = line.lastIndexOf(word)
            if (index2 >= 0) {
                lineResult[index2] = number
            }
        }
        val filteredResult = lineResult.filterNotNull()
        val endResult = (listOf(filteredResult.first()) + filteredResult.last())
        println("$line => $lineResult => $filteredResult => $endResult")
        endResult.joinToString("").toInt()
    }
}
