package dev.mfazio.aoc.twentyone.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test giant squid bingo test data`() {
        val testValues = getResourceAsListOfStrings("dayfour.txt")

        val result = giantSquidBingo(testValues)

        assertEquals(4512, result)
    }
}