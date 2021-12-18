package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartOneTest {
    @Test
    fun `Test get path count`() {
        val input = InputHelpers.getListOfStringsFromFile("/daytwelve.txt")

        Assertions.assertEquals(10, getPathCount(input))
    }
}