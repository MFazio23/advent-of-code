package dev.mfazio.aoc.shared.types

data class DirectionalPoint<T>(
    val point: Point<T>,
    val direction: Direction,
)
