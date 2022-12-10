package dev.mfazio.aoc.twentytwo.dayeight

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test part two`() {
        assertEquals(
            8,
            funPartTwo(
                this::class.getResourceAsListOfStrings("day-eight.txt")
            )
        )
    }
}