package dev.mfazio.aoc.twentyfour.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partTwo(
                getResourceAsListOfStrings("day-five.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partTwo(input: List<String>): Int {
    val rules = parseRules(input)
    val manuals = parseManuals(input)

    val invalidManuals = manuals.filter { manual ->
        !rules.all { rule -> rule.isManualValid(manual) }
    }

    val pageCounts = countPages(invalidManuals, rules)

    return pageCounts.sumOf { manual ->
        manual.firstOrNull { it.lowerCount == it.higherCount }?.page ?: -1
    }
}

fun countPages(
    manuals: List<List<Int>>, rules: List<Rule>
): List<List<PageCounts>> = manuals.map { manual ->
    manual.map { page ->
        val lowers = rules.count { rule ->
            rule.latterPage == page && rule.formerPage in manual
        }
        val highers = rules.count { rule ->
            rule.formerPage == page && rule.latterPage in manual
        }

        PageCounts(page, lowers, highers)
    }
}