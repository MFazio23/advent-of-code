package dev.mfazio.aoc.twentythree.dayseventeen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 102

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-seventeen.txt")
        )

        assertEquals(expected, result)
    }
    @Test
    fun `Test part one super small`() {
        val expected = 7

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-seventeen-super-small.txt")
        )

        assertEquals(expected, result)
    }
}
