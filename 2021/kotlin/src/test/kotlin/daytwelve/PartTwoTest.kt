package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {
    @Test
    fun `Test get path count with double small cave visits`() {
        val input = InputHelpers.getListOfStringsFromFile("/daytwelve.txt")

        Assertions.assertEquals(36, getPathCount(input, 2))
    }
}