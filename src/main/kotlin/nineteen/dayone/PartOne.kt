package dev.mfazio.aoc.nineteen.dayone

import dev.mfazio.aoc.Util
import kotlin.math.floor

fun main() {
    val input = Util.getInput("nineteen/dayone/day-one-input.txt")

    val inputList = input.lines()

    val fuelCosts = inputList.map(::getFuelCost)

    println(fuelCosts.sum())

}

fun getFuelCost(inputString: String) = inputString.toDoubleOrNull()?.let { inputDouble ->
    (floor(inputDouble / 3.0) - 2.0).toInt()
} ?: 0

fun getFuelCost(inputInt: Int) = getFuelCost(inputInt.toString())