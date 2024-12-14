package dev.mfazio.aoc.twentyfour.dayfourteen

import dev.mfazio.utils.extensions.getResourceAsListOfStrings
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PartOneTest {

    @Test
    fun `test part one`() {
        val input = getResourceAsListOfStrings("day-fourteen-small.txt")

        val result = partOne(input, width = 11, height = 7, intervals = 100)

        assertEquals(12, result)
    }

    @Test
    fun `Test extra robot movement - (+x, +y)`() {
        val robot = Robot(2, 6, 1, 2)

        val locations = listOf(
            2 to 6,
            3 to 1,
            4 to 3,
            5 to 5,
            6 to 0,
            7 to 2,
        )

        locations.forEachIndexed { index, (x, y) ->
            val newRobot = robot.move(11, 7, index)
            println(newRobot)
            assertEquals(x, newRobot.x)
            assertEquals(y, newRobot.y)
        }
    }

    @Test
    fun `Test robot movement - (+x, -y)`() {
        val robot = Robot(2, 4, 2, -3)

        val locations = listOf(
            2 to 4,
            4 to 1,
            6 to 5,
            8 to 2,
            10 to 6,
            1 to 3
        )

        locations.forEachIndexed { index, (x, y) ->
            val newRobot = robot.move(11, 7, index)
            println(newRobot)
            assertEquals(x, newRobot.x)
            assertEquals(y, newRobot.y)
        }
    }

    @Test
    fun `Test extra robot movement - (-x, +y)`() {
        val robot = Robot(0, 5, -4, 2)

        val locations = listOf(
            0 to 5,
            7 to 0,
            3 to 2,
            10 to 4,
            6 to 6,
        )

        locations.forEachIndexed { index, (x, y) ->
            val newRobot = robot.move(11, 7, index)
            println(newRobot)
            assertEquals(x, newRobot.x)
            assertEquals(y, newRobot.y)
        }
    }

    @Test
    fun `Test extra robot movement - (-x, -y)`() {
        val robot = Robot(5, 1, -2, -4)

        val locations = listOf(
            5 to 1,
            3 to 4,
            1 to 0,
            10 to 3,
            8 to 6,
            6 to 2,
            4 to 5
        )

        locations.forEachIndexed { index, (x, y) ->
            val newRobot = robot.move(11, 7, index)
            println(newRobot)
            assertEquals(x, newRobot.x)
            assertEquals(y, newRobot.y)
        }
    }
}
