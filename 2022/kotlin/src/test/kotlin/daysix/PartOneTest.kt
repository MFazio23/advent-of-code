package dev.mfazio.aoc.twentytwo.daysix

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.getResourceAsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        assertEquals(
            10,
            funPartOne(
                this::class.getResourceAsString("day-six.txt")
            )
        )
    }
}