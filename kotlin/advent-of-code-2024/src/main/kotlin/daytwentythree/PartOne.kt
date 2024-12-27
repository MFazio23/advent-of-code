package dev.mfazio.aoc.twentyfour.daytwentythree

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.crossProduct
import dev.mfazio.utils.extensions.printEach

suspend fun main() {
    runPuzzle(
        inputFileName = "day-twentythree.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Int {
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

    val trios = computerConnections.flatMap { (computer, connected) ->
        val combos = connected
            .crossProduct(connected)
            .map { (a, b) -> listOf(a,b).distinct().sorted() }
            .filter { it.size == 2 }
            .filter { (a, b) ->
                connections.any { (first, second) -> first == a && second == b || first == b && second == a }
            }
            .distinct()

        combos.map { (a, b) -> listOf(computer, a, b).sorted() }
    }.distinct()

    val historianConnections = trios.filter { (a, b, c) ->
        a.startsWith("t") || b.startsWith("t") || c.startsWith("t")
    }

    return historianConnections.size
}

