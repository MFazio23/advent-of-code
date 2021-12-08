package dev.mfazio.aoc.twentyone.dayeight

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test all digits added together from one line`() {
        val input = "acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"

        assertEquals(5353, sevenSegmentSearchExtra(listOf(input)))
    }

    @Test
    fun `Test all digits added together`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayeight.txt")

        assertEquals(61229, sevenSegmentSearchExtra(input))
    }
}