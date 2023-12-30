package dev.mfazio.aoc.twentythree.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        val expected = 21

        val result = partOne(
            this::class.getResourceAsListOfStrings("day-twelve.txt")
        )

        assertEquals(expected, result)
    }
}
