package dev.mfazio.aoc.twentyone.daynine

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test total risk factor`() {
        val input = InputHelpers.getListOfStringsFromFile("/daynine.txt")

        assertEquals(15, calculateTotalRiskLevel(input))
    }
}