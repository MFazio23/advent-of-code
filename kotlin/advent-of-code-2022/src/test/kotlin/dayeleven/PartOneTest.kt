package dev.mfazio.aoc.twentytwo.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test part one`() {
        assertEquals(
            10605,
            funPartOne(
                this::class.getResourceAsListOfStrings("day-eleven.txt"),
                20
            ).toInt()
        )
    }
}