package dev.mfazio.aoc.twentytwo.daytwenty

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        assertEquals(
            3,
            funPartOne(
                this::class.getResourceAsListOfStrings("day-twenty.txt")
            )
        )
    }
}