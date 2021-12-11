package dev.mfazio.aoc.twentyone.daynine

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test multiplied total basin factor from small dataset`() {
        val input = InputHelpers.getListOfStringsFromFile("/daynine-small.txt")

        assertEquals(1134, calculateTotalBasinProduct(input, 3))
    }

    @Test
    fun `Test multiplied total basin factor`() {
        val input = InputHelpers.getListOfStringsFromFile("/daynine.txt")

        assertEquals(1134, calculateTotalBasinProduct(input, 3))
    }

}