package dev.mfazio.aoc.shared.types

import kotlin.math.abs

data class Point<T>(
    val data: T? = null,
    val x: Int,
    val y: Int,
) {
    val row: Int = y
    val col: Int = x
    val coordinates = "($x, $y)"
    val id = "$x,$y[$data]"

    fun isSameLocation(other: Point<T>): Boolean = this.x == other.x && this.y == other.y

    fun isOrthogonal(other: Point<T>): Boolean =
        this.x == other.x && (this.y == other.y - 1 || this.y == other.y + 1) ||
            this.y == other.y && (this.x == other.x - 1 || this.x == other.x + 1)

    fun isDiagonal(other: Point<T>): Boolean =
        this.x == other.x - 1 && (this.y == other.y - 1 || this.y == other.y + 1) ||
            this.x == other.x + 1 && (this.y == other.y - 1 || this.y == other.y + 1)

    fun isAdjacent(other: Point<T>): Boolean =
        this.isOrthogonal(other) || this.isDiagonal(other)

    fun getAdjacentPoints(points: List<Point<T>>?): List<Point<T>> =
        points?.filter { this.isAdjacent(it) } ?: emptyList()

    fun getBasicNeighbors(points: List<Point<T>>?): Map<NeighborType, Point<T>?> =
        mapOf(
            NeighborType.Upper to points?.firstOrNull { it.x == this.x && it.y == this.y - 1 },
            NeighborType.Lower to points?.firstOrNull { it.x == this.x && it.y == this.y + 1 },
            NeighborType.Left to points?.firstOrNull { it.x == this.x - 1 && it.y == this.y },
            NeighborType.Right to points?.firstOrNull { it.x == this.x + 1 && it.y == this.y },
        )

    fun getDiagonalNeighbors(points: List<Point<T>>?): Map<NeighborType, Point<T>?> =
        mapOf(
            NeighborType.UpperLeft to points?.firstOrNull { it.x == this.x - 1 && it.y == this.y - 1 },
            NeighborType.UpperRight to points?.firstOrNull { it.x == this.x + 1 && it.y == this.y - 1 },
            NeighborType.LowerLeft to points?.firstOrNull { it.x == this.x - 1 && it.y == this.y + 1 },
            NeighborType.LowerRight to points?.firstOrNull { it.x == this.x + 1 && it.y == this.y + 1 },
        )

    fun getAllNeighbors(points: List<Point<T>>?): Map<NeighborType, Point<T>?> =
        getBasicNeighbors(points) + getDiagonalNeighbors(points)

    fun nextInLine(grid: List<Point<T>>, neighborType: NeighborType): Point<T>? {
        val xOffset = when (neighborType) {
            NeighborType.Left, NeighborType.UpperLeft, NeighborType.LowerLeft -> -1
            NeighborType.Right, NeighborType.UpperRight, NeighborType.LowerRight -> 1
            else -> 0
        }
        val yOffset = when (neighborType) {
            NeighborType.Upper, NeighborType.UpperLeft, NeighborType.UpperRight -> -1
            NeighborType.Lower, NeighborType.LowerLeft, NeighborType.LowerRight -> 1
            else -> 0
        }
        return grid.firstOrNull { it.x == this.x + xOffset && it.y == this.y + yOffset }
    }

    fun getDistanceTo(other: Point<T>): Int = abs(this.x - other.x) + abs(this.y - other.y)

    fun getLineSlope(otherPoint: Point<T>): Double {
        val xDiff = abs(this.x - otherPoint.x)
        val yDiff = abs(this.y - otherPoint.y)

        return (yDiff.toDouble() / xDiff.toDouble())
    }

    companion object {
        fun getPointsFromInput(input: List<String>): List<Point<String>> =
            input.mapIndexed { y, line ->
                line.mapIndexed { x, d ->
                    Point(d.toString(), x, y)
                }
            }.flatten()

        fun <T> arePointsInLine(points: List<Point<T>>): Boolean =
            points.size <= 2 || points.crossProduct(points).map { (firstPoint, secondPoint) ->
                if (firstPoint != secondPoint) {
                    firstPoint.getLineSlope(secondPoint)
                } else -1.0
            }.let { slopes ->
                val validSlopes = slopes.filter { it >= 0.0 }
                validSlopes.all { it == validSlopes.first() }
            }
    }
}

fun <T> List<Point<T>>.printPoints(
    includedPoints: List<Point<T>>? = null,
    inclusionText: String? = null,
    exclusionText: String? = null,
) {
    val maxX = this.maxOfOrNull { it.x } ?: run {
        println("No max 'X' found")
        return
    }
    val maxY = this.maxOfOrNull { it.y } ?: run {
        println("No max 'Y' found")
        return
    }

    (0..maxY).forEach { y ->
        (0..maxX).forEach { x ->
            val point = this.firstOrNull { it.x == x && it.y == y }
            if (includedPoints == null || includedPoints.contains(point)) {
                print(inclusionText ?: point?.data ?: "*")
            } else {
                print(exclusionText ?: point?.data ?: ".")
            }
        }
        println()
    }
}

fun <T> List<Point<T>>.sorted(): List<Point<T>> = this.sortedWith(compareBy(Point<T>::y, Point<T>::x))

fun <T> List<Point<T>>.calculatePerimeter(): Int = this.sumOf { point ->
    point.getBasicNeighbors(this).count { (_, neighbor) -> !this.contains(neighbor) }
}

fun <T> List<Point<T>>.calculateSides(grid: List<Point<T>>): Int = this.sumOf { point ->
    val neighbors = point.getAllNeighbors(grid)

    neighbors.filter { (type, _) -> type.isDiagonal }.count { (type, cornerNeighbor) ->
        val orthogonalTypes = type.getAssociatedTypes()
        val orthogonalNeighbors = neighbors.filter { (type, _) -> type in orthogonalTypes }

        (orthogonalNeighbors.all { (_, neighbor) -> point.data != neighbor?.data }) ||
        (orthogonalNeighbors.all { (_, neighbor) -> point.data == neighbor?.data } && cornerNeighbor?.data != point.data)
    }
}

fun <T> List<Point<T>>.sortIntoQuadrants(grid: List<Point<T>>): Map<Quadrant, List<Point<T>>> =
    sortIntoQuadrants(grid.maxOfOrNull { it.x } ?: 0, grid.maxOfOrNull { it.y } ?: 0)

fun <T> List<Point<T>>.sortIntoQuadrants(width: Int, height: Int): Map<Quadrant, List<Point<T>>> =
    this.groupBy { point ->
        when {
            point.x < width / 2 && point.y < height / 2 -> Quadrant.UpperLeft
            point.x > width / 2 && point.y < height / 2 -> Quadrant.UpperRight
            point.x < width / 2 && point.y > height / 2 -> Quadrant.LowerLeft
            point.x > width / 2 && point.y > height / 2 -> Quadrant.LowerRight
            else -> Quadrant.None
        }
    }

//TODO: Move this to fazio-utils-jvm
fun <T> List<T>.crossProduct(otherList: List<T>): List<Pair<T, T>> =
    this.flatMap { first ->
        otherList.map { second ->
            first to second
        }
    }