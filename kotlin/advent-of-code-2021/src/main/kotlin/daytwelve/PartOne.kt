package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun getPathCount(input: List<String>, smallCaveVisits: Int = 1): Int {
    val connections = input.map { inputString -> inputString.split("-") }
    val points = connections.flatten().distinct().map { PathPoint.fromString(it) }

    val possibilities = points.associateWith { spot ->
        connections.mapNotNull { (start, end) ->
            when (spot.value) {
                start -> points.firstOrNull { !it.isStartingPoint() && it.value == end }
                end -> points.firstOrNull { !it.isStartingPoint() && it.value == start }
                else -> null
            }
        }
    }
    val startingPoint = points.firstOrNull { it.isStartingPoint() } ?: return -1

    val paths = getPaths(startingPoint, possibilities, listOf(startingPoint), smallCaveVisits)

    return paths.size
}

fun getPaths(point: PathPoint, possibilities: Map<PathPoint, List<PathPoint>>, path: List<PathPoint>, smallCaveVisits: Int): List<List<PathPoint>> {

    if (point.isEndingPoint()) return listOf(path)

    val currentPossibilities =
        possibilities[point]?.filter { possiblePoint ->
            possiblePoint.isLarge ||
                !path.contains(possiblePoint) ||
                !path.any { point -> path.count { !it.isLarge && it == point } == smallCaveVisits }
        } ?: emptyList()

    return currentPossibilities.map { newPoint ->
        getPaths(newPoint, possibilities, path + newPoint, smallCaveVisits)
    }.flatten()
}

data class PathPoint(
    val value: String,
    val isLarge: Boolean,
) {
    fun isStartingPoint() = value == "start"
    fun isEndingPoint() = value == "end"

    companion object {
        fun fromString(input: String) =
            PathPoint(
                input,
                input == input.uppercase()
            )
    }
}

fun main() {
    val input = getResourceAsListOfStrings("/daytwelve.txt")

    val result = getPathCount(input)

    println(result)
}