package dev.mfazio.aoc.twentythree.daysix

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 71503

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-six.txt")
        )

        assertEquals(expected, result)
    }
}