package dev.mfazio.aoc.twentyone.dayeleven

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {
    @Test
    fun `Test all flash`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayeleven.txt")

        Assertions.assertEquals(195, getAllFlashStep(input))
    }
}