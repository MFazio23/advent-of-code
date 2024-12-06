package dev.mfazio.aoc.twentyfour.dayfive

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    measureTimeMillis {
        println(
            partOne(
                getResourceAsListOfStrings("day-five.txt")
            )
        )
    }.also {
        println("Time taken: $it ms")
    }
}

fun partOne(input: List<String>): Int {
    val rules = parseRules(input)
    val manuals = parseManuals(input)

    val validManuals = manuals.filter { manual ->
        rules.all { rule -> rule.isManualValid(manual) }
    }

    val manualMedians = validManuals.map { manual ->
        manual[manual.size / 2]
    }

    return manualMedians.sum()
}

fun parseRules(input: List<String>): List<Rule> =
    input.filter { it.length == 5 }.map { Rule.fromList(it.split("|")) }

fun parseManuals(input: List<String>): List<List<Int>> =
    input.filter { it.length > 5 }.map {
        manual -> manual.split(",").map { it.toInt() }
    }

data class Rule(
    val formerPage: Int,
    val latterPage: Int,
) {
    fun isManualValid(manual: List<Int>): Boolean {
        val formerIndex = manual.indexOf(formerPage)
        val latterIndex = manual.indexOf(latterPage)

        return formerIndex < 0 || latterIndex < 0 || formerIndex < latterIndex
    }

    fun containsPage(page: Int) = formerPage == page || latterPage == page

    fun containsPages(pageA: Int, pageB: Int) = containsPage(pageA) && containsPage(pageB)

    fun otherPage(page: Int) = when (page) {
        formerPage -> latterPage
        latterPage -> formerPage
        else -> null
    }

    companion object {
        fun fromList(list: List<String>): Rule {
            val (formerPage, latterPage) = list.map { it.toInt() }

            return Rule(formerPage, latterPage)
        }
    }
}