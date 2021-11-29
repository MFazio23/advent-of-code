package dev.mfazio.aoc.nineteen.dayone

import dev.mfazio.aoc.Util

fun main() {
    val input = Util.getInput("nineteen/dayone/day-one-input.txt")

    val inputList = input.lines()

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