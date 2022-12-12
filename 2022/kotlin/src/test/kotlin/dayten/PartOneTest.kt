package dev.mfazio.aoc.twentytwo.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        assertEquals(
            13140,
            funPartOne(
                this::class.getResourceAsListOfStrings("day-ten.txt"),
                listOf(20, 60, 100, 140, 180, 220)
            )
        )
    }

    @Test
    fun `Mini test part`() {
        val testData = listOf(
            "noop",
            "addx 3",
            "addx -5"
        )
        assertEquals(
            1,
            funPartOne(
                testData,
                listOf(1, 3, 4, 5)
            )
        )
    }
}