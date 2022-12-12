package dev.mfazio.aoc.twentytwo.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test part two`() {
        assertEquals(
            2713310158,
            funPartTwo(
                this::class.getResourceAsListOfStrings("day-eleven.txt"),
                10_000
            )
        )
    }
}