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
}