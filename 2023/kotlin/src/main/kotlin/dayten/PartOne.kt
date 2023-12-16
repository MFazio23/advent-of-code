package dev.mfazio.aoc.twentythree.dayten

import dev.mfazio.aoc.twentythree.Point
import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-ten.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val points = findPointsFromInput(input)
    val paths = findPaths(points)

    return paths
        .flatMap { path ->
            path.map { point ->
                point to path.indexOf(point)
            }
        }
        .groupBy { (point, _) -> point }
        .mapValues { (_, list) -> list.minOf { (_, count) -> count } }
        .maxOf { (_, count) -> count }
}

fun findPointsFromInput(input: List<String>): List<Point<String>> = input.mapIndexed { row, line ->
    line.split("").filter { it.isNotBlank() }.mapIndexed { col, point ->
        Point(
            point,
            col,
            row
        )
    }
}.flatten()

fun <T> findPaths(points: List<Point<T>>, startingPoint: Point<T>? = null): List<List<Point<T>>> {
    val start = startingPoint ?: points.first { it.data == "S" }

    val paths = start.findAdjacentPipes(points)?.map {  point ->
        findPath(start, point, points)
    }

    return paths ?: emptyList()
}

fun <T> findPath(startingPoint: Point<T>, point: Point<T>, points: List<Point<T>>): List<Point<T>> {
    val path = mutableListOf(startingPoint, point)
    var nextPoint = point.findAdjacentPipes(points)?.firstOrNull { !path.contains(it) }

    while (nextPoint != null) {
        path.add(nextPoint)
        nextPoint = nextPoint.findAdjacentPipes(points)?.firstOrNull { !path.contains(it) }
    }

    return path
}

fun <T> Point<T>.findAdjacentPipes(points: List<Point<T>>): List<Point<T>>? {
    val orthogonalPoints = when (this.data) {
        "S" -> points.filter { this.isOrthogonal(it) }
        "|" -> points.filter { it.x == this.x && it.y == this.y - 1 || it.x == this.x && it.y == this.y + 1 }
        "-" -> points.filter { it.x == this.x - 1 && it.y == this.y || it.x == this.x + 1 && it.y == this.y }
        "L" -> points.filter { it.x == this.x + 1 && it.y == this.y || it.x == this.x && it.y == this.y - 1 }
        "J" -> points.filter { it.x == this.x - 1 && it.y == this.y || it.x == this.x && it.y == this.y - 1 }
        "7" -> points.filter { it.x == this.x - 1 && it.y == this.y || it.x == this.x && it.y == this.y + 1 }
        "F" -> points.filter { it.x == this.x + 1 && it.y == this.y || it.x == this.x && it.y == this.y + 1 }
        "." -> null
        else -> null
    }

    return orthogonalPoints?.filter {
        it.data != "." && when (it.data) {
            "|" -> it.x == this.x && (it.y == this.y - 1 || it.y == this.y + 1)
            "-" -> it.y == this.y && (it.x == this.x + 1 || it.x == this.x - 1)
            "L" -> it.x == this.x - 1 && it.y == this.y || it.x == this.x && it.y == this.y + 1
            "J" -> it.x == this.x + 1 && it.y == this.y || it.x == this.x && it.y == this.y + 1
            "7" -> it.x == this.x + 1 && it.y == this.y || it.x == this.x && it.y == this.y - 1
            "F" -> it.x == this.x - 1 && it.y == this.y || it.x == this.x && it.y == this.y - 1
            else -> false
        }
    }
}