package dev.mfazio.aoc.eighteen.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = 14

        val result = partTwo(
            listOf("+7", "+7", "-2", "-7", "-4")
        )

        assertEquals(expected, result)
    }
}