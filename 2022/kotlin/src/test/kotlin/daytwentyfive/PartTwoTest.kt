package dev.mfazio.aoc.twentytwo.daytwentyfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.getResourceAsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {
    @Test
    fun `Test part two small`() {
        assertEquals(
            4890,
            funPartTwo(
                this::class.getResourceAsListOfStrings("day-twentyfive.txt")
            )
        )
    }
}