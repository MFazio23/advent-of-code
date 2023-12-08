package dev.mfazio.aoc.twentythree.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    println(
        partOne(
            getResourceAsListOfStrings("day-seven.txt")
        )
    )
}

fun partOne(input: List<String>): Int {
    val hands = input.map { line ->
        val (hand, bid) = line.split(" ")
        CamelCardHand(hand, bid.toInt(), normalCardStrength)
    }

    return hands.sorted().mapIndexed { index, hand -> (index + 1) * hand.bid }.sum()
}

val normalCardStrength = mapOf(
    "A" to 14,
    "K" to 13,
    "Q" to 12,
    "J" to 11,
    "T" to 10,
    "9" to 9,
    "8" to 8,
    "7" to 7,
    "6" to 6,
    "5" to 5,
    "4" to 4,
    "3" to 3,
    "2" to 2,
)

data class CamelCardHand(
    val hand: String,
    val bid: Int,
    val cardStrength: Map<String, Int>,
    val isJoker: Boolean = false
) : Comparable<CamelCardHand> {
    val normalCards = hand.trim().split("").filter { it.isNotBlank() }
    val jokerCards = normalCards.map {
        if (it == "J") {
            if (normalCards.distinct().size == 5) {
                normalCards.maxBy { card -> cardStrength[card] ?: 0 }
            } else {
                normalCards
                    .filter { card -> card != "J" }
                    .groupingBy { card -> card }
                    .eachCount()
                    .maxByOrNull { (_, value) -> value }?.key ?: it
            }
        } else {
            it
        }
    }
    val jokerHand = jokerCards.joinToString("")

    val handType = (if (isJoker) jokerCards else normalCards).let { cards ->
        when {
            cards.all { it == cards.first() } -> HandType.FiveOfAKind
            cards.distinct().size == 2 -> {
                if (cards.groupBy { it }.values.any { it.size == 4 }) {
                    HandType.FourOfAKind
                } else {
                    HandType.FullHouse
                }
            }

            cards.distinct().size == 3 -> {
                if (cards.groupBy { it }.values.any { it.size == 3 }) {
                    HandType.ThreeOfAKind
                } else {
                    HandType.TwoPair
                }
            }

            cards.distinct().size == 4 -> {
                HandType.Pair
            }

            else -> HandType.Single
        }
    }

    fun isStronger(other: CamelCardHand) = this.handType.ordinal > other.handType.ordinal ||
            (this.handType.ordinal == other.handType.ordinal && this.isHandBetter(other))

    fun isHandBetter(other: CamelCardHand): Boolean {
        normalCards.forEachIndexed { index, card ->
            val otherCard = other.normalCards[index]
            if ((cardStrength[card] ?: 0) > (cardStrength[otherCard] ?: 0)) {
                return true
            } else if ((cardStrength[card] ?: 0) < (cardStrength[otherCard] ?: 0)) {
                return false
            }
        }
        return false
    }

    override fun compareTo(other: CamelCardHand): Int = when {
        this.hand == other.hand -> 0
        this.isStronger(other) -> 1
        else -> -1
    }
}

enum class HandType {
    Single,
    Pair,
    TwoPair,
    ThreeOfAKind,
    FullHouse,
    FourOfAKind,
    FiveOfAKind,
}