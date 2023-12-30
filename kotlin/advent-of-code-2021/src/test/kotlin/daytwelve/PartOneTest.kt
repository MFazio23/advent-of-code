package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartOneTest {
    @Test
    fun `Test get path count`() {
        val input = getResourceAsListOfStrings("/daytwelve.txt")

        Assertions.assertEquals(10, getPathCount(input))
    }
}