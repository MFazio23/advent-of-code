package dev.mfazio.aoc.twentyone.dayten

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartOneTest {
    @Test
    fun `Test calculate syntax error score`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayten.txt")

        Assertions.assertEquals(26397, calculateSyntaxErrorScore(input))
    }

    @Test
    fun `Test get incomplete lines`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayten.txt")

        val incompleteLines = InputHelpers.getListOfStringsFromFile("/dayten-incomplete.txt")

        Assertions.assertEquals(incompleteLines, getIncompleteLines(input))
    }
}