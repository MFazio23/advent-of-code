package dev.mfazio.aoc.twentythree.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 5905

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-seven.txt")
        )

        assertEquals(expected, result)
    }
}