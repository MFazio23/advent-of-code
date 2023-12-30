package dev.mfazio.aoc.twentythree.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 467835

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-three.txt")
        )

        assertEquals(expected, result)
    }
}