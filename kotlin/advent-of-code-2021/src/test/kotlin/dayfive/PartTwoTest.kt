package dev.mfazio.aoc.twentyone.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test overlapping vents with diagonals`() {
        val testValues = getResourceAsListOfStrings("dayfive.txt")

        val result = hydrothermalVentureDiagonal(testValues)

        Assertions.assertEquals(12, result)
    }
}