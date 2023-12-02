package dev.mfazio.aoc.twentytwo

import dev.mfazio.utils.extensions.printEach
import kotlin.math.roundToInt

// This is for trying random stuff out quickly. It's effectively a scratch file.
// Also, it keeps my package names in line.

fun main() {
    val a = Route("A", 4, "")
    val b = Route("B", 7, "")

    println(a + b)
}

data class Route(
    val name: String,
    val miles: Int,
    val notes: String,
) {
}

operator fun Route.plus(otherRoute: Route) = "${this.miles + otherRoute.miles} miles"