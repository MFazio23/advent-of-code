package dev.mfazio.aoc.twentythree.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 13

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-four.txt")
        )

        assertEquals(expected, result)
    }
}