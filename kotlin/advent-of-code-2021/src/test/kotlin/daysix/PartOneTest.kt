package dev.mfazio.aoc.twentyone.daysix

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

internal class PartOneTest {

    @Test
    fun `Test lanternfish count for 5 days`() {
        val input = getResourceAsListOfStrings("daysix.txt")

        input.firstOrNull()?.let { fishList ->
            assertEquals(10, getLanternfishCount(fishList, 5))
        } ?: fail("No data found!")
    }

    @Test
    fun `Test lanternfish count for 18 days`() {
        val input = getResourceAsListOfStrings("daysix.txt")

        input.firstOrNull()?.let { fishList ->
            assertEquals(26, getLanternfishCount(fishList, 18))
        } ?: fail("No data found!")
    }

    @Test
    fun `Test lanternfish count for 80 days`() {
        val input = getResourceAsListOfStrings("daysix.txt")

        input.firstOrNull()?.let { fishList ->
            assertEquals(5934, getLanternfishCount(fishList, 80))
        } ?: fail("No data found!")
    }
}