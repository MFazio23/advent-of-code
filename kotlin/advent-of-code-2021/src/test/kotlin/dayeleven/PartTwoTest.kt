package dev.mfazio.aoc.twentyone.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {
    @Test
    fun `Test all flash`() {
        val input = getResourceAsListOfStrings("dayeleven.txt")

        Assertions.assertEquals(195, getAllFlashStep(input))
    }
}