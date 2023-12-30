package dev.mfazio.aoc.twentyone.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test something`() {
        val input = getResourceAsListOfStrings("/dayseven.txt")

        input.firstOrNull()?.split(",")?.let { inputValues ->

            val result = calculateExpensiveCrabFuel(inputValues)

            Assertions.assertEquals(168, result)
        } ?: Assertions.fail("No data found!")
    }
}