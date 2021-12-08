package dev.mfazio.aoc.twentyone.dayeight

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun sevenSegmentSearchExtra(input: List<String>): Int {

    val inputPairs = getInputPairs(input)

    val displayNumbers = inputPairs.map { pair ->
        val digits = getDigits(pair.first)

        val displayDigits = getDisplayDigits(pair.second, digits)

        displayDigits
    }

    return displayNumbers.sum()
}

fun getDisplayDigits(displayStrings: List<String>, digits: List<Digit>): Int =
    displayStrings.joinToString("") { displayString ->
        (digits.firstOrNull {
            it.segments.toList().sorted() == displayString.toList().sorted()
        }?.number ?: -1).toString()
    }.toInt()

fun getDigits(input: List<String>): List<Digit> = input.map { digitString ->
    val number = when (digitString.length) {
        2 -> 1
        3 -> 7
        4 -> 4
        7 -> 8
        else -> getTrickyDigitNumber(input, digitString)
    }

    Digit(number, digitString)
}

fun getTrickyDigitNumber(input: List<String>, digitString: String): Int {

    val nineString = input.first { inputString ->
        inputString.length == 6 && input.first { it.length == 4 }.all { inputString.contains(it) }
    }

    return when {
        nineString == digitString -> 9
        digitString.length == 6 && input.first { it.length == 3 }.all { digitString.contains(it) } -> 0
        digitString.length == 6 -> 6
        digitString.length == 5 && input.first { it.length == 3 }.all { digitString.contains(it) } -> 3
        digitString.length == 5 && digitString.all { c -> nineString.contains(c) } -> 5
        digitString.length == 5 -> 2
        else -> -1
    }
}


data class Digit(
    val number: Int,
    val segments: String,
) {
    override fun toString(): String = "$segments: $number"
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/dayeight.txt")

    val result = sevenSegmentSearchExtra(input)

    println(result)
}