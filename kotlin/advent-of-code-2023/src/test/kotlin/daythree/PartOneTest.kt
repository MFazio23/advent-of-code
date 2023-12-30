package dev.mfazio.aoc.twentythree.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 4361

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-three.txt")
        )

        assertEquals(expected, result)
    }
}