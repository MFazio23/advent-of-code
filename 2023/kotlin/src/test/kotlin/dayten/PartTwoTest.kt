package dev.mfazio.aoc.twentythree.dayten

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two simple`() {
        val expected = 1

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-ten.txt")
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two`() {
        val expected = 4

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-ten-part-two.txt")
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two squish`() {
        val expected = 4

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-ten-part-two-squish.txt")
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two larger`() {
        val expected = 8

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-ten-part-two-larger.txt")
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two largerer`() {
        val expected = 10

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-ten-part-two-largerer.txt")
        )

        assertEquals(expected, result)
    }
}
