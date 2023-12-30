package dev.mfazio.aoc.twentyone.dayfifteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartOneTest {

    @Test
    fun `Test least risky path`() {
        val input = getResourceAsListOfStrings("/dayfifteen.txt")

        Assertions.assertEquals(40, findLeastRiskyPath(input))
    }
}