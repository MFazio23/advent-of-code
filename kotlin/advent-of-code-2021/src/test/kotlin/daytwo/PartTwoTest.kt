package dev.mfazio.aoc.twentyone.daytwo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test dive with aim results`() {
        val testValues = listOf(
            "forward 5",
            "down 5",
            "forward 8",
            "up 3",
            "down 8",
            "forward 2",
        )
        Assertions.assertEquals(
            900,
            diveWithAim(testValues)
        )
    }
}