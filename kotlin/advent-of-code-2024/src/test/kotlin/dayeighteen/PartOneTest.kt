package dev.mfazio.aoc.twentyfour.dayeighteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 0

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-eighteen.txt")
        )

        assertEquals(expected, result)
    }
}
