package dev.mfazio.aoc.twentyone.daythree

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test dive results`() {
        val testValues = InputHelpers.getListOfStringsFromFile("/daythree.txt")
        Assertions.assertEquals(
            230,
            diagnosticRatings(testValues)
        )
    }
}