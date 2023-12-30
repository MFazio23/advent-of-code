package dev.mfazio.aoc.twentytwo.daynine

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test part two`() {
        assertEquals(
            36,
            funPartTwo(
                this::class.getResourceAsListOfStrings("day-nine-part-two.txt")
            )
        )
    }
}