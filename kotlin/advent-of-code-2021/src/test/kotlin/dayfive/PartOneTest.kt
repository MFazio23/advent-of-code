package dev.mfazio.aoc.twentyone.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test overlapping vents`() {
        val testValues = getResourceAsListOfStrings("/dayfive.txt")

        val result = hydrothermalVenture(testValues)

        assertEquals(5, result)
    }

    @Test
    fun `Test large amounts of overlapping vents`() {
        val testValues = getResourceAsListOfStrings("/dayfive-full.txt")

        val result = hydrothermalVenture(testValues)

        assertEquals(6311, result)
    }
}