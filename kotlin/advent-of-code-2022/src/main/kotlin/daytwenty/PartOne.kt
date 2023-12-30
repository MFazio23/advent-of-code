package dev.mfazio.aoc.twentytwo.daytwenty

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import java.util.*
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.sign

fun main() {
    val input = getResourceAsListOfStrings("day-twenty.txt")
    println(funPartOne(input))
}
fun funPartOne(input: List<String>): Int {

    var result = input.map { it.toInt() }

    println("Item count=${result.size}")

    input.map { it.toInt() }.forEachIndexed { index, entry ->
        println("#$index: Input=${input.size}, Result=${result.size}, Entry=$entry")
        val newIndex = when {
            entry >= 0 -> (index + entry) % input.size - 1
            else -> index - (entry % input.size) - 1
        } % input.size

        result = result.toMutableList().apply {
            this.removeAt(index)
            this.add(newIndex, entry)
        }
        //println("CurrentResult=$result")
    }

    println("Expected=${listOf(1, 2, -3, 4, 0, 3, -2)}")
    println("Result=${result}")

    val zeroIndex = result.indexOf(0) + 1

    val keyValues = listOf(
        result[(1000%result.size + zeroIndex)%result.size-1],
        result[(2000%result.size + zeroIndex)%result.size-1],
        result[(3000%result.size + zeroIndex)%result.size-1]
    )

    println(keyValues)

    return keyValues.sum()
}