package dev.mfazio.aoc.shared.types

enum class Direction {
    Up,
    Down,
    Left,
    Right;

    fun isHorizontal() = this == Left || this == Right
    fun isVertical() = this == Up || this == Down

    fun turnLeft(): Direction = when (this) {
        Up -> Left
        Down -> Right
        Left -> Down
        Right -> Up
    }

    fun turnRight(): Direction = when (this) {
        Up -> Right
        Down -> Left
        Left -> Up
        Right -> Down
    }
}