package dev.mfazio.aoc.twentyone.daythirteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartOneTest {
    @Test
    fun `Test get visible dot count after folding once`() {
        val input = getResourceAsListOfStrings("/daythirteen.txt")

        Assertions.assertEquals(17, getVisibleDotCount(input, 1))
    }

    @Test
    fun `Test get visible dot count after folding twice`() {
        val input = getResourceAsListOfStrings("/daythirteen.txt")

        Assertions.assertEquals(16, getVisibleDotCount(input))
    }

    @Test
    fun `Test get visible dot count for full data set`() {
        val input = getResourceAsListOfStrings("/daythirteen-full.txt")

        Assertions.assertEquals(743, getVisibleDotCount(input, 1))
    }
}