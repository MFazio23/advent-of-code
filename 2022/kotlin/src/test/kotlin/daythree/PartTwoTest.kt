package dev.mfazio.aoc.twentytwo.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        assertEquals(
            45000,
            getTopThreeCalorieTotals(
                this::class.getResourceAsListOfStrings("day-one.txt")
            )
        )
    }
}