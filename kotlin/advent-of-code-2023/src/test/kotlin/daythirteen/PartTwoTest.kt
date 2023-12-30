package dev.mfazio.aoc.twentythree.daythirteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 400

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-thirteen.txt")
        )

        assertEquals(expected, result)
    }
}
