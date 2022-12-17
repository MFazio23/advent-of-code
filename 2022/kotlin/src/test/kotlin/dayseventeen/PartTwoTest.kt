package dev.mfazio.aoc.twentytwo.dayseventeen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.getResourceAsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test part two small`() {
        assertEquals(
            6,
            funPartTwo(
                this::class.getResourceAsString("day-seventeen.txt"),
                3
            )
        )
    }

    @Test
    fun `Test part two`() {
        assertEquals(
            17,
            funPartTwo(
                this::class.getResourceAsString("day-seventeen.txt"),
                10
            )
        )
    }

    @Test
    fun `Test part two with part one full`() {
        assertEquals(
            3068,
            funPartTwo(
                this::class.getResourceAsString("day-seventeen.txt"),
                2022
            )
        )
    }
}