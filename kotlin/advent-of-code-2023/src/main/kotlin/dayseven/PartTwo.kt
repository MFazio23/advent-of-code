package dev.mfazio.aoc.twentythree.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    println(
        partTwo(
            getResourceAsListOfStrings("day-seven.txt")
        )
    )
}

val jokerCardStrength = mapOf(
    "A" to 14,
    "K" to 13,
    "Q" to 12,
    "T" to 10,
    "9" to 9,
    "8" to 8,
    "7" to 7,
    "6" to 6,
    "5" to 5,
    "4" to 4,
    "3" to 3,
    "2" to 2,
    "J" to 1,
)

fun partTwo(input: List<String>): Int {
    val hands = input.map { line ->
        val (hand, bid) = line.split(" ")
        CamelCardHand(hand, bid.toInt(), jokerCardStrength, true)
    }

    val sortedHands = hands.sorted()

    return sortedHands.mapIndexed { index, hand -> (index + 1) * hand.bid }.sum()
}
