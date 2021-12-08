package dev.mfazio.aoc.twentyone.dayeight

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test seven segment search - 1, 4, 7, 8`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayeight.txt")

        assertEquals(26, sevenSegmentSearch(input))
    }
}