package dev.mfazio.aoc.twentytwo.dayseventeen

import dev.mfazio.utils.extensions.getResourceAsString
import dev.mfazio.utils.extensions.printEach
import kotlin.system.measureTimeMillis

fun main() {
    val input = getResourceAsString("day-seventeen.txt")
    println(funPartTwo(input, 15_000))
}

fun funPartTwo(input: String?, rounds: Long): Long {
    if (input == null) return -1

    val windActions = getWindActions(input)
    var windStep = 0

    val rocks = Rock.all()

    var filledPoints = listOf<Point>()

    val timings: TimedActionMap = mutableMapOf<TimedAction, Long>().withDefault { 0L }

    var height = 0L

    var minY = 0L

    (0 until rounds).forEach { round ->
        timings.addTime(TimedAction.PrintRoundNumber) {
            if (round % 1_000 == 0L) println("Round ${"%,d".format(round)}")
        }
        val rock = rocks[(round % 5L).toInt()]
        var x = 2L
        var y = height + 3L

        var doneMoving = false

        while (!doneMoving) {
            timings.addTime(TimedAction.WindShiftCalculation) {
                val windShift = getWindShift(windActions, windStep, rock, x, y, filledPoints)
                windStep++

                x += windShift
            }

            timings.addTime(TimedAction.CheckingForEnd) {
                if (y <= minY || rock.isHittingOtherRocks(y - 1L, x, filledPoints)) {
                    doneMoving = true
                } else {
                    y--
                }
            }
        }

        timings.addTime(TimedAction.AddingNewPoints) {
            filledPoints = filledPoints + (rock.getOffsetPoints(y, x))
        }

        timings.addTime(TimedAction.SortFilledPoints) {
            //filledPoints = filledPoints.sortedBy { it.y }
        }

        timings.addTime(TimedAction.RemovingOldPoints) {
            /*if (round % 10 == 0L) {
                val groupedPoints = filledPoints.groupBy { it.x }
                val mappedPoints = groupedPoints.map { (x, yList) -> x to yList.maxOf { it.y } }
                minY = mappedPoints.minOf { (_, y) -> y }

                filledPoints = filledPoints.filter { it.y >= minY }
            }*/
        }

        //printFilledPoints(filledPoints)

        height = filledPoints.maxOf { it.y } + 1L
    }

    println("Filled points=${filledPoints.size}")

    timings.forEach { (action, time) ->
        println("$action=${time}ms")
    }

    val fives = filledPoints.groupBy { it.y }.map { (_, point) -> point.map { it.x } }.windowed(150, 1, false)

    fives.groupingBy { it }.eachCount().forEach { (items, count) ->
        println("$count => $items")
    }

    fives.groupingBy { it }.eachCount().values.groupingBy { it }.eachCount().forEach { (rowCount, countCount) ->
        println("$countCount => $rowCount")
    }

    println("totalTime=${timings.values.sum()}ms")

    return height
}

enum class TimedAction { AddingNewPoints, CheckingForEnd, RemovingOldPoints, WindShiftCalculation, PrintRoundNumber, SortFilledPoints, }

typealias TimedActionMap = MutableMap<TimedAction, Long>

fun TimedActionMap.addTime(action: TimedAction, amount: Long) =
    this.merge(action, amount, Long::plus)

fun TimedActionMap.addTime(action: TimedAction, timedEvent: () -> Unit): Long? =
    this.addTime(action, measureTimeMillis(timedEvent))