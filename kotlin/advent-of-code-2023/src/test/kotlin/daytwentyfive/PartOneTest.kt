package dev.mfazio.aoc.twentythree.daytwentyfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 54

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-twentyfive.txt")
        )

        assertEquals(expected, result)
    }
}
