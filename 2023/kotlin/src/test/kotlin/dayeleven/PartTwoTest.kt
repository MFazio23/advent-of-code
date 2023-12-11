package dev.mfazio.aoc.twentythree.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two 10`() {
        val expected = 1030L

        val result = totalPathDistanceWithExpandedGalaxies(
            this::class.getResourceAsListOfStrings("day-eleven.txt"),
            10
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two 100`() {
        val expected = 8410L

        val result = totalPathDistanceWithExpandedGalaxies(
            this::class.getResourceAsListOfStrings("day-eleven.txt"),
            100
        )

        assertEquals(expected, result)
    }
}
