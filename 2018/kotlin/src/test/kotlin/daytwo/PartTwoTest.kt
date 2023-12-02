package dev.mfazio.aoc.eighteen.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test part two`() {
        val expected = "fgij"

        val input = """
            abcde
            fghij
            klmno
            pqrst
            fguij
            axcye
            wvxyz
        """.trimIndent().split("\n")

        val result = partTwo(
            input
        )

        assertEquals(expected, result)
    }
}