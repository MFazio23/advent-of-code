package dev.mfazio.aoc.twentyone.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test calculate incomplete syntax lines score`() {
        val input = getResourceAsListOfStrings("/dayten.txt")

        Assertions.assertEquals(288957, calculateSyntaxIncompleteLineScore(input))
    }
}