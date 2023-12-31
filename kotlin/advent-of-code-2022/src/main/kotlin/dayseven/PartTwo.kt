package dev.mfazio.aoc.twentytwo.dayseven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-seven.txt")

    println(funPartTwo(input))
}

const val totalSize = 70_000_000
const val updateSize = 30_000_000

fun funPartTwo(input: List<String>): Int {

    val structure = getFileStructure(input)

    return if (structure != null) {
        val freeSpace = totalSize - structure.getSize()

        println("Free space=$freeSpace")

        val remainingSpace = updateSize - freeSpace

        println("Remaining space=$remainingSpace")

        return getFolders(structure)
            .filter { it.getSize() >= remainingSpace }
            .minByOrNull { it.getSize() }?.getSize()
            ?: -1
    } else -1
}