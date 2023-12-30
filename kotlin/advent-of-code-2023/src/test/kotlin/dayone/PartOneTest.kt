package dev.mfazio.aoc.twentythree.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 142

        val result = getTotalCalibrationValues(
            this::class.getResourceAsListOfStrings("day-one.txt")
        )

        assertEquals(expected, result)
    }
}