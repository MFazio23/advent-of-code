package dev.mfazio.aoc.twentyfour.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwoQuick(
                getResourceAsListOfStrings("day-five.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwoQuick(input: List<String>): Int {
    val rules = parseRules(input)
    val manuals = parseManuals(input)

    val invalidManuals = manuals.filter { manual ->
        !rules.all { rule -> rule.isManualValid(manual) }
    }

    println("Invalid manuals")
    invalidManuals.printEach()

    val reSortedManuals = reSortManuals(invalidManuals, rules)

    val total = reSortedManuals.sumOf { manual ->
        val midpoint = manual.firstOrNull { it.lowerCount == it.higherCount }
        println("====================================")
        println(midpoint)
        println("====================================")
        midpoint?.page ?: -1
    }.also {
        println("Sum of midpoints: $it")
    }

    println()
    println("Re-sorted manuals")
    reSortedManuals.printEach()

    return total
}

fun reSortManuals(manuals: List<List<Int>>, rules: List<Rule>): List<List<PageCounts>> {
    val sortedManuals = manuals.map { manual ->
        val pages = manual.map { page ->
            val lowers = rules.count { rule ->
                rule.latterPage == page && rule.formerPage in manual
            }
            val highers = rules.count { rule ->
                rule.formerPage == page && rule.latterPage in manual
            }

            PageCounts(page, lowers, highers)
        }.sortedBy { (_, lowers) -> lowers }

        pages
    }

    return sortedManuals
}

data class PageCounts(
    val page: Int,
    val lowerCount: Int,
    val higherCount: Int,
)