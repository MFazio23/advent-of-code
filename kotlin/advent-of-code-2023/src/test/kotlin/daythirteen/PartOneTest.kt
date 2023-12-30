package dev.mfazio.aoc.twentythree.daythirteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 405

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-thirteen.txt")
        )

        assertEquals(expected, result)
    }
}
