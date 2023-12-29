package dev.mfazio.aoc.twentythree.dayfourteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two with one billion cycles`() {
        val expected = 64

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 1_000_000_000
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two with one cycle`() {
        val expected = 87

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 1
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two with two cycles`() {
        val expected = 69

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 2
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two with three cycles`() {
        val expected = 69

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 3
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two with ten cycles`() {
        val expected = 69

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 10
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two with fifteen cycles`() {
        val expected = 63

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 15
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two with twenty-three cycles`() {
        val expected = 68

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 23
        )

        assertEquals(expected, result)
    }

    @Test
    fun `Test part two with twenty-seven cycles`() {
        val expected = 64

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-fourteen.txt"),
            cycles = 27
        )

        assertEquals(expected, result)
    }
}
