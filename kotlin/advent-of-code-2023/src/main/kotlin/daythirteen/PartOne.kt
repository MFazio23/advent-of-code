package dev.mfazio.aoc.twentythree.daythirteen

import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-thirteen.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int = getBlocks(input).sumOf { block ->
    findReflectionTotal(block)
}

fun getBlocks(input: List<String>): List<List<Point<String>>> {
    val points = mutableListOf<List<Point<String>>>()
    val currentBlock = mutableListOf<Point<String>>()
    var y = 0

    input.forEach { line ->
        if (line.isNotBlank()) {
            line.split("").filter { it.isNotBlank() }.forEachIndexed { x, s ->
                currentBlock.add(Point(s, x, y))
            }
            y++
        } else {
            y = 0
            points.add(currentBlock.toList())
            currentBlock.clear()
        }
    }

    points.add(currentBlock)

    return points
}

fun findReflectionTotal(block: List<Point<String>>, checkNum: Int = 0): Int {
    val height = block.maxOf { it.y } + 1
    val width = block.maxOf { it.x } + 1

    val horizontalReflection = findReflection(block, width, checkNum, { it.x }, { it.y }) ?: 0
    val verticalReflection = findReflection(block, height, checkNum, { it.y }, { it.x }) ?: 0

    return (horizontalReflection) + (100 * (verticalReflection))
}

fun findReflection(
    block: List<Point<String>>,
    size: Int,
    checkNum: Int = 0,
    splitFunction: (Point<String>) -> Int,
    groupFunction: (Point<String>) -> Int,
): Int? =
    (0..size - 2).firstNotNullOfOrNull { index ->
        val (a, b) = block.partition { splitFunction(it) <= index }

        val isFirstHalf = a.size <= b.size

        val firstSize = index + 1
        val secondSize = size - firstSize
        val actualFirst = if (isFirstHalf) a else a.filter { (size - secondSize * 2 until size).contains(splitFunction(it)) }
        val actualSecond = if (isFirstHalf) b.filter { (firstSize until firstSize * 2).contains(splitFunction(it)) } else b

        val firstRows = actualFirst.groupBy(groupFunction)
        val secondRows = actualSecond.groupBy(groupFunction)

        val differences = firstRows.map { (ind, firstPoints) ->
            secondRows[ind]?.let { secondPoints ->
                firstPoints.joinToString("") {
                    it.data.orEmpty() } to secondPoints.reversed().joinToString("") { it.data.orEmpty()
                }
            }?.let { (first, second) ->
                first.zip(second).count { (f, s) -> f != s }
            } ?: 0
        }

        if (differences.sum() == checkNum) firstSize else null
    }

fun printBlock(block: List<Point<String>>) {
    block.sortedWith(compareBy({ it.y }, { it.x })).groupBy { it.y }.forEach { (y, points) ->
        points.map { it.data }.forEach { print(it) }
        points.map { " - ${it.x},${it.y}" }.forEach { print(it) }
        println()
    }
}