package dev.mfazio.aoc.twentythree.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 525152

        val result = partTwo(
            this::class.getResourceAsListOfStrings("day-twelve.txt")
        )

        assertEquals(expected, result)
    }
}
