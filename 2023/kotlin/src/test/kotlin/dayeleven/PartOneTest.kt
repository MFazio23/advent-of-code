package dev.mfazio.aoc.twentythree.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 374

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-eleven.txt")
        )

        assertEquals(expected, result)
    }
}
