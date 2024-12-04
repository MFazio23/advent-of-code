package dev.mfazio.aoc.twentyfour.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 0

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-four.txt")
        )

        assertEquals(expected, result)
    }
}
