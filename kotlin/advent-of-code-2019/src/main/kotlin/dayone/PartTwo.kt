package dev.mfazio.aoc.nineteen.dayone

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val inputList = getResourceAsListOfStrings("day-one-input.txt")

    val fuelCosts = mutableListOf<Int>()

    inputList.forEach { inputString ->
        var cost = getFuelCost(inputString)

        while(cost > 0) {
            fuelCosts.add(cost)

            cost = getFuelCost(cost)
        }
    }

    println(fuelCosts.sum())
}