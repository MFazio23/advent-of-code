package dev.mfazio.aoc.shared.types

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PointTest {
    @Test
    fun `Test get opposite direction`() {
        assertEquals(CardinalDirection.North, CardinalDirection.South.getOpposite())
    }
}