package dev.mfazio.aoc.twentythree.dayeight

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 2L

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-eight.txt")
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part one longer`() {
        val expected = 6L

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-eight-part-one-longer.txt")
        )

        assertEquals(expected, result)
    }
}
