package dev.mfazio.aoc.twentyone.daytwo

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test dive results`() {
        val testValues = getResourceAsListOfStrings("daytwo.txt")
        Assertions.assertEquals(
            150,
            dive(testValues)
        )
    }
}