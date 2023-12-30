package dev.mfazio.aoc.twentytwo.dayseventeen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.getResourceAsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one small`() {
        assertEquals(
            6,
            funPartOne(
                this::class.getResourceAsString("day-seventeen.txt"),
                3
            )
        )
    }

    @Test
    fun `Test part one`() {
        assertEquals(
            17,
            funPartOne(
                this::class.getResourceAsString("day-seventeen.txt"),
                10
            )
        )
    }

    @Test
    fun `Test part one full`() {
        assertEquals(
            3068,
            funPartOne(
                this::class.getResourceAsString("day-seventeen.txt"),
                2022
            )
        )
    }
}