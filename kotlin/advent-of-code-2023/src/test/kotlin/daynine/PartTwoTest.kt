package dev.mfazio.aoc.twentythree.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 2

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-nine.txt")
        )

        assertEquals(expected, result)
    }
}
