package dev.mfazio.aoc.twentythree.dayfourteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 136

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-fourteen.txt")
        )

        assertEquals(expected, result)
    }
}
