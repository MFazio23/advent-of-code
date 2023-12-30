package dev.mfazio.aoc.twentythree.dayfifteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 145

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fifteen.txt")
        )

        assertEquals(expected, result)
    }
}
