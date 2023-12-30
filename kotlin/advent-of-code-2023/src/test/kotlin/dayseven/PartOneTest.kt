package dev.mfazio.aoc.twentythree.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 6440

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-seven.txt")
        )

        assertEquals(expected, result)
    }
}