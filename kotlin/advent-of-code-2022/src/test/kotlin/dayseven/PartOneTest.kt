package dev.mfazio.aoc.twentytwo.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        assertEquals(
            95437,
            funPartOne(
                this::class.getResourceAsListOfStrings("day-seven.txt")
            )
        )
    }
}