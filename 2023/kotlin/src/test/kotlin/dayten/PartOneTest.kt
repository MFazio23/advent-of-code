package dev.mfazio.aoc.twentythree.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 4

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-ten.txt")
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part one complex`() {
        val expected = 8

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-ten-complex.txt")
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part one complex-er`() {
        val expected = 8

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-ten-complexer.txt")
        )

        assertEquals(expected, result)
    }
}
