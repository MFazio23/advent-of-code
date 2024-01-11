package dev.mfazio.aoc.shared.types

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CardinalDirectionTest {
    @Test
    fun `Test get opposite direction`() {

        assertEquals(CardinalDirection.North, CardinalDirection.South.getOpposite())
        assertEquals(CardinalDirection.South, CardinalDirection.North.getOpposite())
        assertEquals(CardinalDirection.East, CardinalDirection.West.getOpposite())
        assertEquals(CardinalDirection.West, CardinalDirection.East.getOpposite())
    }
}