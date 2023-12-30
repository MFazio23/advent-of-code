package dev.mfazio.aoc.twentyone.dayfour

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun giantSquidBingoLoser(input: List<String>): Int {

    val (calledNumbers, bingoBoards) = getNumbersAndBoardsFromInput(input)

    val winnerList = mutableListOf<BingoBoard>()

    (0..calledNumbers.size).forEachIndexed { index, _ ->
        val numbers = calledNumbers.subList(0, index)

        bingoBoards.forEach { board ->
            if (board.isWinner(numbers) && !winnerList.contains(board)) {
                winnerList.add(board)
            }
            if (winnerList.containsAll(bingoBoards)) {
                return winnerList.last().getScore(numbers)
            }
        }
    }

    return -1
}

fun main() {
    val input = getResourceAsListOfStrings("/dayfour.txt")

    val result = giantSquidBingoLoser(input)

    println(result)
}