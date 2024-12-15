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

    fun getAssociatedTypes() = when (this) {
        UpperLeft -> listOf(Upper, Left)
        UpperRight -> listOf(Upper, Right)
        LowerLeft -> listOf(Lower, Left)
        LowerRight -> listOf(Lower, Right)
        else -> listOf(this)
    }
}