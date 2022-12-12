package dev.mfazio.aoc.twentytwo.dayeleven

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import java.util.*

fun main() {
    val input = getResourceAsListOfStrings("day-eleven.txt")
    println(funPartOne(input, 20))
}

fun funPartOne(input: List<String>, rounds: Int): Long {
    var monkeys = getMonkeys(input)

    repeat(rounds) {
        println("=== === === Round $it === === ===")
        monkeys = takeRound(monkeys)
    }

    monkeys.values.forEach { monkey ->
        println("Monkey #${monkey.id} (${monkey.inspectionCount} inspections): ${monkey.items.map { it.worry }}")
    }

    val (top, second) = monkeys.values.sortedByDescending { it.inspectionCount }.take(2)

    return top.inspectionCount * second.inspectionCount
}

fun takeRound(monkeys: Map<Int, Monkey>): Map<Int, Monkey> {
    val newMonkeys = monkeys.toMutableMap()
    monkeys.forEach { (id, monkey) ->
        monkey.items.forEach { item ->
            println("Item=$item")
            println("BeforeItems=${monkey.items}")
            monkey.inspectionCount++
            println("StartWorry=${item.worry}")
            item.worry = monkey.operation(item)
            println("PostOpWorry=${item.worry}")
            item.worry = item.worry / 3
            println("TestedWorry=${item.worry}")
            println("Test=${monkey.test(item)}")
            if (monkey.test(item)) {
                newMonkeys[monkey.trueThrow]?.let { updatedMonkey ->
                    println("Throw ${item.worry} to Monkey #${updatedMonkey.id}")
                    updatedMonkey.items.add(item)
                }
            } else {
                newMonkeys[monkey.falseThrow]?.let { updatedMonkey ->
                    println("Throw ${item.worry} to Monkey #${updatedMonkey.id}")
                    updatedMonkey.items.add(item)
                }
            }
            println("AfterItems=${monkey.items}")
            println("FilteredItems=${monkey.items.filter { it.id != item.id }}")
            //newMonkeys[id] = monkey.copy(items = monkey.items.filter { it.id != item.id })
            newMonkeys.values.forEach { monkey ->
                println("Monkey #${monkey.id}: ${monkey.items.map { it.worry }}")
            }
        }
        monkey.items.clear()
    }

    return newMonkeys
}

fun getMonkeys(input: List<String>): Map<Int, Monkey> {
    val monkeys = mutableMapOf<Int, Monkey>()

    var currentMonkey = Monkey()

    input.forEach { line ->
        when {
            line.contains("Monkey") -> {
                val id = line.replace("Monkey ", "").replace(":", "").toInt()
                currentMonkey = currentMonkey.copy(id = id)
            }

            line.contains("Starting") -> {
                val items = line.replace("  Starting items: ", "").split(", ").map { Item(worry = it.toLong()) }
                currentMonkey = currentMonkey.copy(items = items.toMutableList())
            }

            line.contains("Operation") -> {
                val operation = getOperation(line)
                currentMonkey = currentMonkey.copy(operation = operation)
            }

            line.contains("Test") -> {
                val test = line.replace("  Test: divisible by ", "").toLong()
                currentMonkey = currentMonkey.copy(test = test)
            }

            line.contains("If true") -> {
                val trueThrow = line.replace("    If true: throw to monkey ", "").toInt()
                currentMonkey = currentMonkey.copy(trueThrow = trueThrow)
            }

            line.contains("If false") -> {
                val falseThrow = line.replace("    If false: throw to monkey ", "").toInt()
                currentMonkey = currentMonkey.copy(falseThrow = falseThrow)
            }

            else -> {
                monkeys[currentMonkey.id] = currentMonkey
            }
        }
    }

    monkeys[currentMonkey.id] = currentMonkey

    return monkeys
}

fun getOperation(line: String): (Item) -> Long {
    val (operator, value) = line.replace("  Operation: new = old ", "").split(" ")

    val operation = if (operator == "+") {
        { a: Long, b: Long -> a + b }
    } else {
        { a: Long, b: Long -> a * b }
    }

    val valueOperation = value.toLongOrNull()?.let { { it } } ?: { a: Long -> a }

    return { v -> operation(v.worry, valueOperation(v.worry)) }
}

data class Monkey(
    var id: Int = -1,
    var inspectionCount: Long = 0L,
    val items: MutableList<Item> = mutableListOf(),
    val operation: (Item) -> Long = { -1 },
    val test: Long = 0L,
    val trueThrow: Int = -1,
    val falseThrow: Int = -1,
) {
    fun test(item: Item) = item.worry % test == 0L
}

data class Item(
    val id: UUID = UUID.randomUUID(),
    var worry: Long,
)