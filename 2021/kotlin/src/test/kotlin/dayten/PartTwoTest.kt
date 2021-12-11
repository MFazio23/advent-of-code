package dev.mfazio.aoc.twentyone.dayten

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test calculate incomplete syntax lines score`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayten.txt")

        Assertions.assertEquals(288957, calculateSyntaxIncompleteLineScore(input))
    }
}