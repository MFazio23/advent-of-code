package dev.mfazio.aoc.twentythree.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 30

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-four.txt")
        )

        assertEquals(expected, result)
    }
    @Test
    fun `Test part two quick`() {
        val expected = 30

        val result = partTwoQuick(
            this::class.getResourceAsListOfStrings("day-four.txt")
        )

        assertEquals(expected, result)
    }
}