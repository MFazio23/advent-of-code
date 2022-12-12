package dev.mfazio.aoc.twentytwo.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings

fun main() {
    val input = getResourceAsListOfStrings("day-eleven.txt")
    println(funPartTwo(input, 10_000))
}

fun funPartTwo(input: List<String>, rounds: Int): Long {
    var monkeys = getMonkeys(input)

    // Thanks Todd!
    // https://todd.ginsberg.com/post/advent-of-code/2022/day11/
    val totalTest = monkeys.values.map { it.test }.reduce(Long::times)

    repeat(rounds) {
        if (it % 100 == 0) {
            println("=== === === Round $it === === ===")
            monkeys.values.forEach { monkey ->
                println("Monkey #${monkey.id}: ${monkey.items.map { it.worry }}")
            }
        }
        monkeys = takePanickedRound(monkeys, totalTest)
    }

    monkeys.values.forEach { monkey ->
        println("Monkey #${monkey.id} (${monkey.inspectionCount} inspections): ${monkey.items.map { it.worry }}")
    }

    val (top, second) = monkeys.values.sortedByDescending { it.inspectionCount }.take(2)

    return top.inspectionCount * second.inspectionCount
}

fun takePanickedRound(monkeys: Map<Int, Monkey>, totalTest: Long): Map<Int, Monkey> {
    val newMonkeys = monkeys.toMutableMap()
    monkeys.forEach { (id, monkey) ->
        monkey.items.forEach { item ->
            monkey.inspectionCount++
            item.worry = monkey.operation(item)
            item.worry = item.worry % totalTest
            if (monkey.test(item)) {
                newMonkeys[monkey.trueThrow]?.let { updatedMonkey ->
                    updatedMonkey.items.add(item)
                }
            } else {
                newMonkeys[monkey.falseThrow]?.let { updatedMonkey ->
                    updatedMonkey.items.add(item)
                }
            }
        }
        monkey.items.clear()
    }

    return newMonkeys
}