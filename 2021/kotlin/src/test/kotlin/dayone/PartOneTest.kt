package dev.mfazio.aoc.twentyone.dayone

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test reportRepair for 2020`() {
        assertEquals(
            1721 * 299,
            reportRepair(
                listOf(
                    1721,
                    979,
                    366,
                    299,
                    675,
                    1456,
                ), 2020
            )
        )
    }
}