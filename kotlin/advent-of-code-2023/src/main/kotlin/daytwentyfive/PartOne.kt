package dev.mfazio.aoc.twentythree.daytwentyfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-twentyfive.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val connections = mutableListOf<List<String>>()
    input.forEach { line ->
        val (main, connectionsString) = line.split(": ")
        val connectionsList = connectionsString.split(" ").map { it.trim() }

        val index = connections.indexOfFirst { c -> c.contains(main) || c.any { connectionsList.contains(it) } }

        if (index < 0) connections.add(listOf(main) + connectionsList)
        else connections[index] = (connections[index] + main + connectionsList).distinct()

        /*connections[main] = connectionsList
        connectionsList.forEach { connection ->
            connections[connection] = (connections[connection] ?: emptyList()) + main
        }*/
    }

    connections.map { it.joinToString()
    }.printEach()

    return -1
}
