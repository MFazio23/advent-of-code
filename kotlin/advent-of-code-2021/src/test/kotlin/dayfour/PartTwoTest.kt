package dev.mfazio.aoc.twentyone.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test giant squid bingo loser test data`() {
        val testValues = getResourceAsListOfStrings("dayfour.txt")

        val result = giantSquidBingoLoser(testValues)

        Assertions.assertEquals(1924, result)
    }
}