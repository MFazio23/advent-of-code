package dev.mfazio.aoc.twentyone.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartOneTest {
    @Test
    fun `Test flash count after 2 steps`() {
        val input = getResourceAsListOfStrings("dayeleven.txt")

        Assertions.assertEquals(35, getFlashCount(input, 2))
    }

    @Test
    fun `Test flash count after 10 steps`() {
        val input = getResourceAsListOfStrings("dayeleven.txt")

        Assertions.assertEquals(204, getFlashCount(input, 10))
    }

    @Test
    fun `Test flash count after 100 steps`() {
        val input = getResourceAsListOfStrings("dayeleven.txt")

        Assertions.assertEquals(1656, getFlashCount(input, 100))
    }
}