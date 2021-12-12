package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class PartTwoTest {
    @Test
    fun `Test part two`() {
        val input = InputHelpers.getListOfStringsFromFile("/daytwelve.txt")

        Assertions.assertEquals(195, partTwo(input))
    }
}