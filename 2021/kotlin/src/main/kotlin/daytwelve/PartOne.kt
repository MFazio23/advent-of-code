package dev.mfazio.aoc.twentyone.daytwelve

import dev.mfazio.aoc.twentyone.util.InputHelpers

fun partOne(input: List<String>): Int {
    val paths = input.map { inputString -> inputString.split("-") }
    val points = paths.flatten().distinct().map { PathPoint.fromString(it) }

    val possibilities = points.associateWith { spot ->
        paths.mapNotNull { (start, end) ->
            when (spot.value) {
                start -> end
                end -> start
                else -> null
            }
        }
    }

    val paths = getPat

    possibilities.get(points.firstOrNull { it.value == "start" })?.

    return -1
}

fun getPath(point: PathPoint, possibilities: List<PathPoint>): List<PathPoint> {

    return emptyList()
}

data class PathPoint(
    val value: String,
    val isLarge: Boolean,
    var isVisited: Boolean = false,
) {
    companion object {
        fun fromString(input: String) =
            PathPoint(
                input,
                input == input.uppercase()
            )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PathPoint

        return value != other.value
    }

    override fun hashCode(): Int {
        var result = value.hashCode()
        result = 31 * result + isLarge.hashCode()
        result = 31 * result + isVisited.hashCode()
        return result
    }
}

fun main() {
    val input = InputHelpers.getListOfStringsFromFile("/daytwelve.txt")

    val result = partOne(input)

    println(result)
}