package dev.mfazio.aoc.twentyfour.daytwentythree

import dev.mfazio.aoc.shared.runPuzzle

suspend fun main() {
    runPuzzle(
        inputFileName = "day-twentythree.txt",
    ) {
        partTwo(it)
    }
}

fun partTwo(input: List<String>): String {
    val connections = input.map { line ->
        line.split("-")
    }

    val computers = connections.flatMap { (a, b) -> listOf(a, b) }.distinct()

    val computerConnections = computers.associateWith { computer ->
        connections
            .filter { (first, second) -> first == computer || second == computer }
            .map { (first, second) ->
                if (first == computer) second else first
            }.distinct()
    }

    val allCombos = computerConnections.map { (computer, conns) ->
        val startingLists = conns.map { listOf(computer, it).sorted() }
        conns.fold(startingLists) { lists, conn ->
            lists.map { list ->
                val isInList = list.all { computerConnections[conn]?.contains(it) == true }
                if (!list.contains(conn) && isInList) {
                    (list + conn).sorted()
                } else list

            }
        }
    }

    val combos = allCombos.flatten().distinct()

    return combos.maxByOrNull { it.size }?.joinToString(",").orEmpty()
}
