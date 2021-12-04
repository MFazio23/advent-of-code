package dev.mfazio.aoc.twentyone.daytwo

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test dive results`() {
        val testValues = InputHelpers.getListOfStringsFromFile("/daytwo.txt")
        Assertions.assertEquals(
            150,
            dive(testValues)
        )
    }
}