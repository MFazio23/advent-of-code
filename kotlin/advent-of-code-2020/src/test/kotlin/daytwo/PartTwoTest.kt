package dev.mfazio.aoc.twenty.daytwo

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PartTwoTest {

    @Test
    fun `Test example passwordPhilosophyPositional values`() {
        assertEquals(
            1,
            passwordPhilosophyPositional(
                listOf(
                    "1-3 a: abcde",
                    "1-3 b: cdefg",
                    "2-9 c: ccccccccc",
                )
            )
        )
    }
}