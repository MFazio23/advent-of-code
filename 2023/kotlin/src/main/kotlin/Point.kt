package dev.mfazio.aoc.twentythree

//TODO: move this to a utils package/library/folder instead.
data class Point<T>(
    val data: T,
    val x: Int,
    val y: Int,
) {
    val row: Int = y
    val col: Int = x

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
}

enum class NeighborType {
    Top,
    Bottom,
    Left,
    Right,
}