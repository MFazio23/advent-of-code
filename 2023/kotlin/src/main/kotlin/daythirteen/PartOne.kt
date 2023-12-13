package dev.mfazio.aoc.twentythree.daythirteen

import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.ceil
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

    val horizontalReflection = findHorizontalReflection(block, width, checkNum) ?: 0
    val verticalReflection = findVerticalReflection(block, height, checkNum) ?: 0

    return (horizontalReflection) + (100 * (verticalReflection))
}

fun findHorizontalReflection(block: List<Point<String>>, width: Int, checkNum: Int = 0): Int? =
    (0..width - 2).firstNotNullOfOrNull { column ->
        val (left, right) = block.partition { it.x <= column }

        val isLeftHalf = left.size <= right.size

        val leftSize = column + 1
        val rightSize = width - leftSize
        val actualLeft = if (isLeftHalf) left else left.filter { (width - rightSize * 2 until width).contains(it.x) }
        val actualRight = if (isLeftHalf) right.filter { (leftSize until leftSize * 2).contains(it.x) } else right

        val leftRows = actualLeft.groupBy { it.y }
        val rightRows = actualRight.groupBy { it.y }

        val differences = leftRows.map { (y, leftPoints) ->
            rightRows[y]?.let { rightPoints ->
                leftPoints.joinToString("") { it.data } to rightPoints.reversed().joinToString("") { it.data }
            }?.let { (left, right) ->
                left.zip(right).count { (l, r) -> l != r }
            } ?: 0
        }

        if (differences.sum() == checkNum) leftSize else null
    }

fun findVerticalReflection(block: List<Point<String>>, height: Int, checkNum: Int = 0): Int? =
    (0..height - 2).firstNotNullOfOrNull { row ->
        val (top, bottom) = block.partition { it.y <= row }

        val isTopHalf = top.size < bottom.size

        val topSize = row + 1
        val bottomSize = height - topSize
        val actualTop = if (isTopHalf) top else top.filter { (height - bottomSize * 2 until height).contains(it.y) }
        val actualBottom = if (isTopHalf) bottom.filter { (topSize until topSize * 2).contains(it.y) } else bottom

        val topColumns = actualTop.groupBy { it.x }
        val bottomColumns = actualBottom.groupBy { it.x }

        val differences = topColumns.map { (x, topPoints) ->
            bottomColumns[x]?.let { bottomPoints ->
                topPoints.joinToString("") { it.data } to bottomPoints.reversed().joinToString("") { it.data }
            }?.let { (top, bottom) ->
                top.zip(bottom).count { (l, r) -> l != r }
            } ?: 0
        }

        if (differences.sum() == checkNum) topSize else null
    }

fun printBlock(block: List<Point<String>>) {
    block.sortedWith(compareBy({ it.y }, { it.x })).groupBy { it.y }.forEach { (y, points) ->
        points.map { it.data }.forEach { print(it) }
        points.map { " - ${it.x},${it.y}" }.forEach { print(it) }
        println()
    }
}