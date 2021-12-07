package dev.mfazio.aoc.twentyone.dayseven

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test something`() {
        val input = InputHelpers.getListOfStringsFromFile("/dayseven.txt")

        input.firstOrNull()?.split(",")?.let { inputValues ->

            val result = calculateCrabFuel(inputValues)

            assertEquals(37, result)
        } ?: fail("No data found!")
    }
}