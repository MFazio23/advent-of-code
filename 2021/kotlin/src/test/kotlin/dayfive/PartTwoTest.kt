package dev.mfazio.aoc.twentyone.dayfive

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test overlapping vents with diagonals`() {
        val testValues = InputHelpers.getListOfStringsFromFile("/dayfive.txt")

        val result = hydrothermalVentureDiagonal(testValues)

        Assertions.assertEquals(12, result)
    }
}