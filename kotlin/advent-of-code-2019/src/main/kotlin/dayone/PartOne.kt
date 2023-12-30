package dev.mfazio.aoc.nineteen.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import kotlin.math.floor

fun main() {
    val inputList = getResourceAsListOfStrings("day-one-input.txt")

    val fuelCosts = inputList.map(::getFuelCost)

    println(fuelCosts.sum())

}

fun getFuelCost(inputString: String) = inputString.toDoubleOrNull()?.let { inputDouble ->
    (floor(inputDouble / 3.0) - 2.0).toInt()
} ?: 0

fun getFuelCost(inputInt: Int) = getFuelCost(inputInt.toString())