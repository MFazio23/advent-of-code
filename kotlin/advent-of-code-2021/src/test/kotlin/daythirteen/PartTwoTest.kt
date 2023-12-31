package dev.mfazio.aoc.twentyone.daythirteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {
    // No assertions - the displays should be verified manually.
    // I'm just using a unit test suite to test multiple data sources.
    @Test
    fun `Test display after folding`() {
        getResourceAsListOfStrings("daythirteen.txt")
    }

    @Test
    fun `Test full display after folding`() {
        getResourceAsListOfStrings("daythirteen-full.txt")
    }
}