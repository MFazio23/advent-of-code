package dev.mfazio.aoc.twentyfour.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 2858L

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-nine-small.txt")
        )

        assertEquals(expected, result)
    }
}
