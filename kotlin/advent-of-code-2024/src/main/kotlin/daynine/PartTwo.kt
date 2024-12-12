package dev.mfazio.aoc.twentyfour.daynine

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-nine.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): Long {
    val diskData = input.firstOrNull() ?: return -1

    val diskMap = diskData.chunked(2).mapIndexed { index, strings ->
        val file = strings.firstOrNull()?.digitToInt() ?: 0
        val freeSpace = strings.lastOrNull()?.digitToInt() ?: 0

        listOf(
            FileSegment(index, file),
            FileSegment(0, freeSpace, true)
        )
    }.flatten().toMutableList()

    diskMap.filter { !it.isBlank }.reversed().forEach { file ->
        val blankIndex = diskMap.indexOfFirst { it.isBlank && it.length >= file.length }

        val fileIndex = diskMap.indexOf(file)

        if (blankIndex < 0 || blankIndex > fileIndex) return@forEach

        diskMap[fileIndex] = FileSegment(0, file.length, true)

        val blank = diskMap[blankIndex]

        diskMap[blankIndex] = file

        if (blank.length > file.length) {
            diskMap.add(blankIndex + 1, FileSegment(blank.id, blank.length - file.length, true))
        }
    }

    return diskMap.flatMap { fileSegment ->
        (0 until fileSegment.length).map { fileSegment.id.toLong() }
    }.mapIndexed { index, l -> index * l }.sum()
}

data class FileSegment(
    val id: Int,
    val length: Int,
    val isBlank: Boolean = false
)
