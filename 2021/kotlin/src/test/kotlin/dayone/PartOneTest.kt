package dev.mfazio.aoc.twentyone.dayone

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test example measurements`() {
        assertEquals(
            7,
            sonarSweep(
                listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
            )
        )
    }
}