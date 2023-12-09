package dev.mfazio.aoc.twentythree.dayeight

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 6L

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-eight-part-two.txt")
        )

        assertEquals(expected, result)
    }
}
