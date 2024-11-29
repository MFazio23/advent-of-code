package types

import dev.mfazio.aoc.shared.types.Point
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PointTest {
    @Test
    fun `test if points are orthogonal`() {
        val orthogonalPoints = listOf(
            listOf(1, 1, 1, 2),
            listOf(1, 1, 2, 1),
            listOf(1, 1, 1, 0),
            listOf(1, 1, 0, 1),
            listOf(3, 5, 3, 4),
            listOf(3, 5, 3, 6),
            listOf(3, 5, 2, 5),
            listOf(3, 5, 4, 5),
        )
        orthogonalPoints.forEach { (x1, y1, x2, y2) ->
            assertTrue(Point("", x1, y1).isOrthogonal(Point("", x2, y2)))
        }
    }
    @Test
    fun `test if points are not orthogonal`() {
        val nonOrthogonalPoints = listOf(
            listOf(0, 1, 1, 2),
            listOf(1, 1, 2, 2),
            listOf(1, 1, 2, 0),
            listOf(1, 1, 0, 0),
            listOf(3, 5, 4, 4),
            listOf(3, 5, 2, 6),
            listOf(3, 5, 2, 4),
            listOf(3, 5, 3, 2),
            listOf(3, 5, 5, 5),
        )
        nonOrthogonalPoints.forEach { (x1, y1, x2, y2) ->
            assertFalse(Point("", x1, y1).isOrthogonal(Point("", x2, y2)))
        }
    }
    @Test
    fun `test if points are diagonal`() {
        val diagonalPoints = listOf(
            listOf(1, 1, 0, 0),
            listOf(1, 1, 2, 2),
            listOf(1, 1, 0, 2),
            listOf(1, 1, 2, 0),
            listOf(3, 5, 2, 4),
            listOf(3, 5, 4, 6),
            listOf(3, 5, 2, 6),
            listOf(3, 5, 4, 4),
        )
        diagonalPoints.forEach { (x1, y1, x2, y2) ->
            assertTrue(Point("", x1, y1).isDiagonal(Point("", x2, y2)))
        }
    }

    @Test
    fun `test if points are not diagonal`() {
        val nonDiagonalPoints = listOf(
            listOf(0, 1, 1, 3),
            listOf(1, 1, 2, 1),
            listOf(1, 1, 4, 0),
            listOf(1, 1, 0, 1),
            listOf(3, 5, 3, 4),
            listOf(3, 5, 3, 6),
            listOf(3, 5, 2, 5),
            listOf(3, 5, 4, 5),
        )
        nonDiagonalPoints.forEach { (x1, y1, x2, y2) ->
            assertFalse(Point("", x1, y1).isDiagonal(Point("", x2, y2)))
        }
    }

    @Test
    fun `test if points are adjacent`() {
        val adjacentPoints = listOf(
            listOf(1, 1, 1, 2),
            listOf(1, 1, 2, 1),
            listOf(1, 1, 1, 0),
            listOf(1, 1, 0, 1),
            listOf(3, 5, 3, 4),
            listOf(3, 5, 3, 6),
            listOf(3, 5, 2, 5),
            listOf(3, 5, 4, 5),
            listOf(1, 1, 0, 0),
            listOf(1, 1, 2, 2),
            listOf(1, 1, 0, 2),
            listOf(1, 1, 2, 0),
            listOf(3, 5, 2, 4),
            listOf(3, 5, 4, 6),
            listOf(3, 5, 2, 6),
            listOf(3, 5, 4, 4),
        )
        adjacentPoints.forEach { (x1, y1, x2, y2) ->
            assertTrue(Point("", x1, y1).isAdjacent(Point("", x2, y2)))
        }
    }

    @Test
    fun `test if points are not adjacent`() {
        val nonAdjacentPoints = listOf(
            listOf(0, 1, 1, 3),
            listOf(1, 1, 3, 1),
            listOf(1, 1, 4, 0),
            listOf(1, 1, 0, 4),
            listOf(3, 5, 3, 2),
            listOf(3, 5, 3, 7),
            listOf(3, 5, 1, 5),
            listOf(3, 5, 8, 5),
        )
        nonAdjacentPoints.forEach { (x1, y1, x2, y2) ->
            assertFalse(Point("", x1, y1).isAdjacent(Point("", x2, y2)))
        }
    }
}