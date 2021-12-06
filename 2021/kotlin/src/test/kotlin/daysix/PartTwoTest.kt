package dev.mfazio.aoc.twentyone.daysix

import dev.mfazio.aoc.twentyone.util.InputHelpers
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class PartTwoTest {

    @Test
    fun `Test efficient lanternfish count for 5 days`() {
        val input = InputHelpers.getListOfStringsFromFile("/daysix.txt")

        input.firstOrNull()?.let { fishList ->
            Assertions.assertEquals(10, getEfficientLanternfishCount(fishList, 5))
        } ?: Assertions.fail("No data found!")
    }

    @Test
    fun `Test efficient lanternfish count for 18 days`() {
        val input = InputHelpers.getListOfStringsFromFile("/daysix.txt")

        input.firstOrNull()?.let { fishList ->
            Assertions.assertEquals(26, getEfficientLanternfishCount(fishList, 18))
        } ?: Assertions.fail("No data found!")
    }

    @Test
    fun `Test efficient lanternfish count for 80 days`() {
        val input = InputHelpers.getListOfStringsFromFile("/daysix.txt")

        input.firstOrNull()?.let { fishList ->
            Assertions.assertEquals(5934, getEfficientLanternfishCount(fishList, 80))
        } ?: Assertions.fail("No data found!")
    }

    @Test
    fun `Test efficient lanternfish count for 256 days`() {
        val input = InputHelpers.getListOfStringsFromFile("/daysix.txt")

        input.firstOrNull()?.let { fishList ->
            Assertions.assertEquals(26984457539L, getEfficientLanternfishCount(fishList, 256))
        } ?: Assertions.fail("No data found!")
    }
}