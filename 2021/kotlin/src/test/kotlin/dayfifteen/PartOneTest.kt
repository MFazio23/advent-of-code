package dev.mfazio.aoc.twentyone.dayfifteen

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartOneTest {

    @Test
    fun `Test least risky path`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayfifteen.txt")

        Assertions.assertEquals(40, findLeastRiskyPath(input))
    }
}