package dev.mfazio.aoc.shared.types

enum class CardinalDirection {
    North,
    South,
    East,
    West;

    fun getOpposite() = when (this) {
        North -> South
        South -> North
        East -> West
        West -> East
    }
}