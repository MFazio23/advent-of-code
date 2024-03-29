package dev.mfazio.aoc.twentyone.daythree

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test dive results`() {
        val testValues = getResourceAsListOfStrings("daythree.txt")
        Assertions.assertEquals(
            198,
            diagnostic(testValues)
        )
    }
}