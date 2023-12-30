package dev.mfazio.aoc.twentytwo.dayeight

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-eight.txt")
    println(funPartTwo(input))
}

fun funPartTwo(input: List<String>): Int {
    val rows = getTreeRows(input)
    val columns = getTreeColumns(input)

    val scores = rows.flatten().map { tree ->
        tree.getScenicScore(rows, columns)
    }

    return scores.max()
}

fun Tree.getScenicScore(rows: List<List<Tree>>, columns: List<List<Tree>>): Int =
    getScenicNumberForSection(rows) * getScenicNumberForSection(columns)

fun Tree.getScenicNumberForSection(trees: List<List<Tree>>): Int =
    trees.find { it.contains(this) }?.let { row ->
        val treeIndex = row.indexOf(this)
        val firstTrees = row.take(treeIndex)
        val lastTrees = row.takeLast(row.size - 1 - treeIndex)

        val firstNum = firstTrees.reversed().takeWhile { it < this }
        val lastNum = lastTrees.takeWhile { it < this }

        val firstOffset = firstNum.lastOrNull()?.let { tree ->
            if (firstTrees.first() == tree) 0 else 1
        } ?: 0

        val lastOffset = lastNum.lastOrNull()?.let { tree ->
            if (lastTrees.last() == tree) 0 else 1
        } ?: 0

        (firstNum.size + firstOffset) * (lastNum.size + lastOffset)
    } ?: 0