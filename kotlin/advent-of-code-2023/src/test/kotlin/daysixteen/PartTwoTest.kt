package dev.mfazio.aoc.twentythree.daysixteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 51

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-sixteen.txt")
        )

        assertEquals(expected, result)
    }
}
