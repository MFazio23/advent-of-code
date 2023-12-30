package dev.mfazio.aoc.twentytwo.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test part two`() {
        assertEquals(
            45000,
            funPartTwo(
                this::class.getResourceAsListOfStrings("day-one.txt")
            )
        )
    }
}