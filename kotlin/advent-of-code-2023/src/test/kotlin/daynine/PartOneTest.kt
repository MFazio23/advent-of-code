package dev.mfazio.aoc.twentythree.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 114

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-nine.txt")
        )

        assertEquals(expected, result)
    }
}
