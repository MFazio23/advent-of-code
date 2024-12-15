package dev.mfazio.aoc.twentyfour.daytwelve

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.aoc.shared.types.calculatePerimeter

suspend fun main() {
    runPuzzle(
        inputFileName = "day-twelve.txt",
    ) {
        partOne(it)
    }
}

fun partOne(input: List<String>): Long {
    val grid = Point.getPointsFromInput(input)

    println("Grid is ${grid.size} points and ${grid.maxOf { it.x }}x${grid.maxOf { it.y }}.")

    val plots = useConnectedNeighbors(grid)

    return plots.filter { it.isNotEmpty() }.sumOf { plot ->
        plot.size * plot.calculatePerimeter().toLong()
    }
}

fun useConnectedNeighbors(grid: List<Point<String>>): List<List<Point<String>>> {
    val plots = mutableListOf<List<Point<String>>>()
    val checkedLocations = mutableSetOf<Point<String>>()

    grid.forEach{ point ->
        val connectedNeighbors = getConnectedNeighbors(grid, point, listOf(point), plots, checkedLocations)
        if (connectedNeighbors.isNotEmpty()) {
            plots.add(connectedNeighbors)
        }
    }

    return plots.toList()
}

fun getConnectedNeighbors(
    grid: List<Point<String>>,
    point: Point<String>,
    connections: List<Point<String>>,
    plots: List<List<Point<String>>>,
    checkedLocations: MutableSet<Point<String>>
): List<Point<String>> {
    if (plots.any { it.contains(point) }) {
        return emptyList()
    }
    checkCounts[point] = checkCounts.getValue(point) + 1
    checkedLocations.add(point)

    val groupNeighbors = point.getBasicNeighbors(grid)
        .filter { (_, neighbor) ->
            neighbor?.data == point.data &&
                !checkedLocations.contains(neighbor) &&
                !connections.contains(neighbor) &&
                plots.none { it.contains(neighbor) }
        }.values.filterNotNull()

    return when {
        groupNeighbors.isEmpty() -> {
            connections
        }

        else -> {
            (connections + groupNeighbors.flatMap { neighbor ->
                getConnectedNeighbors(grid, neighbor, connections + neighbor, plots, checkedLocations)
            }).distinct()
        }
    }
}

val checkCounts = mutableMapOf<Point<String>, Int>().withDefault { 0 }