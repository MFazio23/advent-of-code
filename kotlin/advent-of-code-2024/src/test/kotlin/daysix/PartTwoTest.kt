package dev.mfazio.aoc.twentyfour.daysix

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 0

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-six.txt")
        )

        assertEquals(expected, result)
    }
}
