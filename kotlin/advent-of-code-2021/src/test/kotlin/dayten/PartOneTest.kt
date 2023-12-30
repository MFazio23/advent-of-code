package dev.mfazio.aoc.twentyone.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartOneTest {
    @Test
    fun `Test calculate syntax error score`() {
        val input = getResourceAsListOfStrings("/dayten.txt")

        Assertions.assertEquals(26397, calculateSyntaxErrorScore(input))
    }

    @Test
    fun `Test get incomplete lines`() {
        val input = getResourceAsListOfStrings("/dayten.txt")

        val incompleteLines = getResourceAsListOfStrings("/dayten-incomplete.txt")

        Assertions.assertEquals(incompleteLines, getIncompleteLines(input))
    }
}