package dev.mfazio.aoc.twentyone.dayfifteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {

    @Test
    fun `Test least risky path with expanded cave`() {
        val input = getResourceAsListOfStrings("dayfifteen.txt")

        Assertions.assertEquals(315, findLeastRiskyPathInFiveX(input))
    }
}