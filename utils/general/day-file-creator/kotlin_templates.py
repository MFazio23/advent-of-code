part_template = """package dev.mfazio.aoc.{year}.day{day}

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {{
    runPuzzle(
        inputFileName = "day-{day}.txt",
    ) {{
        {partFunction}(it)
    }}
}}

fun {partFunction}(input: List<String>): Int {{

    return -1
}}
"""

part_test_template = """package dev.mfazio.aoc.{year}.day{day}

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class {partName}Test {{

    @Test
    fun `Test part {partNumber}`() {{
        val expected = 0

        val result = {partFunction}(
            this::class.getResourceAsListOfStrings("day-{day}.txt")
        )

        assertEquals(expected, result)
    }}
}}
"""
