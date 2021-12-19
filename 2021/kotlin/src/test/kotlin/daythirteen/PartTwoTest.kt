package dev.mfazio.aoc.twentyone.daythirteen

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {
    // No assertions - the displays should be verified manually.
    // I'm just using a unit test suite to test multiple data sources.
    @Test
    fun `Test display after folding`() {
        InputHelpers.getListOfStringsFromFile("/daythirteen.txt")
    }

    @Test
    fun `Test full display after folding`() {
        InputHelpers.getListOfStringsFromFile("/daythirteen-full.txt")
    }
}