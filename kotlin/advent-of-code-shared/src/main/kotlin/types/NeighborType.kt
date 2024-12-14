package dev.mfazio.aoc.shared.types

enum class NeighborType {
    Upper,
    Lower,
    Left,
    Right,
    UpperLeft,
    UpperRight,
    LowerLeft,
    LowerRight;

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
}