package dev.mfazio.aoc.twentytwo.daytwentyfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.getResourceAsString
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one small`() {
        assertEquals(
            4890,
            funPartOne(
                this::class.getResourceAsListOfStrings("day-twentyfive.txt")
            )
        )
    }
}