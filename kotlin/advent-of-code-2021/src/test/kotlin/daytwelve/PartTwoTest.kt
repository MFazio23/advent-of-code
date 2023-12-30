package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {
    @Test
    fun `Test get path count with double small cave visits`() {
        val input = getResourceAsListOfStrings("/daytwelve.txt")

        Assertions.assertEquals(36, getPathCount(input, 2))
    }
}