package dev.mfazio.aoc.twentyfour.dayfourteen

import dev.mfazio.aoc.shared.runPuzzle
import dev.mfazio.aoc.shared.types.Point
import dev.mfazio.aoc.shared.types.printPoints
import java.io.PrintStream

suspend fun main() {
    runPuzzle(
        inputFileName = "day-fourteen.txt",
        hidePeriodicMessages = true,
    ) {
        partTwo(
            input = it,
            width = 101,
            height = 103,
        )
    }
}

/*
This one was silly, and I appreciate it.
I scrolled through that file below until I found the tree.
 */

fun partTwo(input: List<String>, width: Int, height: Int): Int {
    var robots = getRobots(input)

    val fileStream = PrintStream("/Users/mfazio23/Development/Files/advent-of-code/day-fourteen-part-two.txt")
    System.setOut(fileStream)

    repeat(10404) { runNum ->
        println("Run ${runNum + 1}")
        val movedRobots = robots.map { robot ->
            robot.move(width, height, 1)
        }

        val robotGrid = movedRobots.map { robot ->
            Point("*", robot.x, robot.y)
        }

        robotGrid.printPoints()

        robots = movedRobots
    }

    return -1
}
