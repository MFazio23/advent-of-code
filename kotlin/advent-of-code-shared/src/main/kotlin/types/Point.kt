package dev.mfazio.aoc.shared.types

data class Point<T>(
    val data: T? = null,
    val x: Int,
    val y: Int,
) {
    val row: Int = y
    val col: Int = x
    val coordinates = "($x, $y)"

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
            NeighborType.Top to points?.firstOrNull { it.x == this.x && it.y == this.y - 1 },
            NeighborType.Bottom to points?.firstOrNull { it.x == this.x && it.y == this.y + 1 },
            NeighborType.Left to points?.firstOrNull { it.x == this.x - 1 && it.y == this.y },
            NeighborType.Right to points?.firstOrNull { it.x == this.x + 1 && it.y == this.y },
        )

    companion object {
        fun getPointsFromInput(input: List<String>): List<Point<String>> =
            input.mapIndexed { y, line ->
                line.mapIndexed { x, d ->
                    Point(d.toString(), x, y)
                }
            }.flatten()
    }
}

fun <T> List<Point<T>>.printPoints() {
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
            print(this.firstOrNull { it.x == x && it.y == y }?.data ?: ".")
        }
        println()
    }
}

enum class NeighborType {
    Top,
    Bottom,
    Left,
    Right,
}