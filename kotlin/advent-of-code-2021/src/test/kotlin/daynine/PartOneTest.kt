package dev.mfazio.aoc.twentyone.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test total risk factor`() {
        val input = getResourceAsListOfStrings("daynine.txt")

        assertEquals(15, calculateTotalRiskLevel(input))
    }
}