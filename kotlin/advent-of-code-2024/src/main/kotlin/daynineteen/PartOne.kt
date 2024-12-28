package dev.mfazio.aoc.twentyfour.daynineteen

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-nineteen.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Int {
    val patterns = input.firstOrNull()?.split(", ") ?: return -1

    val designs = input.drop(2)

    val designCounts = designs.map { design ->
        generateDesign(patterns, design)
    }

    return designCounts.count { it > 0 }
}

fun generateDesign(
    patterns: List<String>,
    design: String,
    cache: MutableMap<String, Long> = mutableMapOf()
): Long = if (design.isEmpty()) 1 else cache.getOrPut(design) {
    patterns.filter { design.startsWith(it) }.sumOf { pattern ->
        generateDesign(patterns, design.removePrefix(pattern), cache)
    }
}