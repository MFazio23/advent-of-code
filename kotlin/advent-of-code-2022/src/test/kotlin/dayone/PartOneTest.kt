package dev.mfazio.aoc.twentytwo.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        assertEquals(
            24000,
            getCalorieTotals(
                this::class.getResourceAsListOfStrings("day-one.txt")
            ).max()
        )
    }
}