package dev.mfazio.aoc.twentyfour.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 0

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-four.txt")
        )

        assertEquals(expected, result)
    }
}
