package dev.mfazio.aoc.twenty.daytwo

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PartOneTest {

    @Test
    fun passwordPhilosophy() {
        assertEquals(
            2,
            passwordPhilosophy(
                listOf(
                    "1-3 a: abcde",
                    "1-3 b: cdefg",
                    "2-9 c: ccccccccc",
                )
            )
        )
    }
}