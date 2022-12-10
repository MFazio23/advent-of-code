package dev.mfazio.aoc.twentytwo.dayeight

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach

fun main() {
    val input = getResourceAsListOfStrings("day-eight.txt")
    println(funPartOne(input))
}

fun funPartOne(input: List<String>): Int {

    val rows = getTreeRows(input)

    val columns = getTreeColumns(input)

    val trees = (rows.flatMap { getSeenTrees(it) } + columns.flatMap { getSeenTrees(it) }).distinct()

    trees.printEach()

    return trees.size
}

fun getTreeRows(input: List<String>): List<List<Tree>> = input.mapIndexed { rowIndex, line ->
    line.mapIndexed { columnIndex, heightChar -> Tree(heightChar.digitToInt(), rowIndex, columnIndex) }
}

fun getTreeColumns(input: List<String>): List<List<Tree>> =
    getTreeRows(input).foldIndexed(mutableListOf<MutableList<Tree>>()) { rowIndex, acc, row ->
        row.forEachIndexed { columnIndex, tree ->
            if (acc.getOrNull(columnIndex) == null) {
                acc.add(mutableListOf())
            }

            val newList = acc[columnIndex].apply {
                add(rowIndex, tree)
            }

            acc[columnIndex] = newList
        }
        acc
    }

fun getSeenTrees(row: List<Tree>): List<Tree> {
    val forward = row.mapIndexed { index, tree ->
        if (row.take(index).none { it >= tree }) tree else null
    }.filterNotNull()

    val backward = row.reversed().let { reversed ->
        reversed.mapIndexed { index, tree ->
            if (reversed.take(index).none { it >= tree }) tree else null
        }.filterNotNull()
    }

    return forward + backward
}

data class Tree(
    val height: Int,
    val row: Int,
    val col: Int,
) {
    operator fun compareTo(otherTree: Tree) = height.compareTo(otherTree.height)

    override fun toString(): String = "${height}H($row,$col)"
}