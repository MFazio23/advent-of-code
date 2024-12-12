package dev.mfazio.aoc.twentyfour.daynine

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-nine.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Long {
    val diskData = input.firstOrNull() ?: return -1

    val diskMap = diskData.chunked(2).mapIndexed { index, strings ->
        val file = strings.firstOrNull()?.digitToInt() ?: 0
        val freeSpace = strings.lastOrNull()?.digitToInt() ?: 0

        (index to file) to freeSpace
    }

    val filesWithIds = diskMap.map { it.first }
    val freeSpaces = diskMap.map { it.second }

    val filePrep = filesWithIds.zip(freeSpaces).map { (fileLength, freeSpace) ->
        val (index, id) = fileLength

        val fileData = (0 until id).map { index.toString() }

        fileData + (0 until freeSpace).map { "." }
    }.flatten().dropLastWhile { "." == it }

    val gapIndexes = filePrep.mapIndexedNotNull { index, str ->
        if (str == ".") index else null
    }

    val toMove = filePrep.filter { it != "." }.reversed().take(gapIndexes.size)

    val movedFile = filePrep.mapIndexed { index, text ->
        if (index in gapIndexes) {
            toMove[gapIndexes.indexOf(index)]
        } else text
    }.dropLast(gapIndexes.size)

    return movedFile.mapIndexed { index, c -> index * c.toLong() }.sum()
}
