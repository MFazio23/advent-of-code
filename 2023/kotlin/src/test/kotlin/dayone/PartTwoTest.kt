package dev.mfazio.aoc.twentythree.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 281

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-one-part-two.txt")
        )

        assertEquals(expected, result)
    }
}