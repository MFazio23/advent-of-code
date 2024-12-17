package dev.mfazio.aoc.shared.types

enum class NeighborType(val isDiagonal: Boolean = false) {
    Upper,
    Lower,
    Left,
    Right,
    UpperLeft(isDiagonal = true),
    UpperRight(isDiagonal = true),
    LowerLeft(isDiagonal = true),
    LowerRight(isDiagonal = true);

    fun getOpposite() = when (this) {
        Upper -> Lower
        Lower -> Upper
        Left -> Right
        Right -> Left
        UpperLeft -> LowerRight
        UpperRight -> LowerLeft
        LowerLeft -> UpperRight
        LowerRight -> UpperLeft
    }

    fun getSides() = when (this) {
        Upper, Lower -> listOf(Left, Right)
        Left, Right -> listOf(Upper, Lower)
        else -> emptyList()
    }

    fun getAssociatedTypes() = when (this) {
        UpperLeft -> listOf(Upper, Left)
        UpperRight -> listOf(Upper, Right)
        LowerLeft -> listOf(Lower, Left)
        LowerRight -> listOf(Lower, Right)
        else -> listOf(this)
    }

    fun getDirection() = when (this) {
        Upper -> Direction.Up
        Lower -> Direction.Down
        Left -> Direction.Left
        Right -> Direction.Right
        else -> null
    }
}