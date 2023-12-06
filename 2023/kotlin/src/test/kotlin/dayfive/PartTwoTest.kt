package dev.mfazio.aoc.twentythree.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 46L

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-five.txt")
        )

        assertEquals(expected, result)
    }
}