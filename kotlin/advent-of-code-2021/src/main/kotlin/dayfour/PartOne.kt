package dev.mfazio.aoc.twentyone.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

typealias BingoBoard = List<List<Int>>

fun giantSquidBingo(input: List<String>): Int {

    val (calledNumbers, bingoBoards) = getNumbersAndBoardsFromInput(input)

    (0..calledNumbers.size).forEachIndexed { index, _ ->
        val numbers = calledNumbers.subList(0, index)
        bingoBoards.firstOrNull { board -> board.isWinner(numbers) }?.let { board ->
            return board.getScore(numbers)
        }
    }

    return -1
}

fun getNumbersAndBoardsFromInput(input: List<String>): Pair<List<Int>, List<BingoBoard>> {
    val calledNumbers = input.firstOrNull()?.split(",")?.map(String::toInt) ?: emptyList()

    val bingoBoards = input
        .drop(2)
        .chunked(6)
        .map { board ->
            board.dropLast(1).map { row ->
                row.trim().split("\\s+".toRegex()).map(String::toInt)
            }
        }

    return calledNumbers to bingoBoards
}

fun BingoBoard.isWinner(pickedNumbers: List<Int>): Boolean {
    val rows = this.any { row ->
        pickedNumbers.containsAll(row)
    }

    val columns = (0 until 5).any { col ->
        pickedNumbers.containsAll(
            this@isWinner.map { it[col] }
        )
    }

    return rows || columns
}

fun BingoBoard.getScore(pickedNumbers: List<Int>) =
    this.flatten().filter { !pickedNumbers.contains(it) }.sum() * (pickedNumbers.lastOrNull() ?: 0)

fun main() {
    val input = getResourceAsListOfStrings("/dayfour.txt")

    val result = giantSquidBingo(input)

    println(result)
}