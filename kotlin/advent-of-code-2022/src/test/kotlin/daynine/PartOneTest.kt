package dev.mfazio.aoc.twentytwo.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        assertEquals(
            13,
            funPartOne(
                this::class.getResourceAsListOfStrings("day-nine.txt")
            )
        )
    }
}