package dev.mfazio.aoc.twentyone.dayone

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test triple sums`() {
        assertEquals(
            5, sonarSweepTriple(
                listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
            )
        )
    }

    @Test
    fun `Test windowed triple sums`() {
        assertEquals(
            5, sonarSweepWindowedTriple(
                listOf(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)
            )
        )
    }
}