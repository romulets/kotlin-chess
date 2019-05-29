package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class QueenTest {

    @Test
    fun testAllSidePossibilities() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        val possiblePositions = queen.possiblePositions { null }

        assertPossiblePositionContains(possiblePositions, 1, 'e')
        assertPossiblePositionContains(possiblePositions, 2, 'e')
        assertPossiblePositionContains(possiblePositions, 3, 'e')
        assertPossiblePositionContains(possiblePositions, 5, 'e')
        assertPossiblePositionContains(possiblePositions, 6, 'e')
        assertPossiblePositionContains(possiblePositions, 7, 'e')
        assertPossiblePositionContains(possiblePositions, 8, 'e')
        assertPossiblePositionContains(possiblePositions, 4, 'a')
        assertPossiblePositionContains(possiblePositions, 4, 'b')
        assertPossiblePositionContains(possiblePositions, 4, 'c')
        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 4, 'g')
        assertPossiblePositionContains(possiblePositions, 4, 'h')

        assertPossiblePositionContains(possiblePositions, 1, 'b')
        assertPossiblePositionContains(possiblePositions, 2, 'c')
        assertPossiblePositionContains(possiblePositions, 3, 'd')
        assertPossiblePositionContains(possiblePositions, 5, 'f')
        assertPossiblePositionContains(possiblePositions, 6, 'g')
        assertPossiblePositionContains(possiblePositions, 7, 'h')
        assertPossiblePositionContains(possiblePositions, 1, 'h')
        assertPossiblePositionContains(possiblePositions, 2, 'g')
        assertPossiblePositionContains(possiblePositions, 3, 'f')
        assertPossiblePositionContains(possiblePositions, 5, 'd')
        assertPossiblePositionContains(possiblePositions, 6, 'c')
        assertPossiblePositionContains(possiblePositions, 7, 'b')
        assertPossiblePositionContains(possiblePositions, 8, 'a')

        assertEquals(27, possiblePositions.size)
    }

    @Test
    fun testEatPossibilities() {
        val queen = Queen(Color.WHITE, Position(5, 'e'))

        val otherPieces = listOf(
            Position(3, 'g'),
            Position(3, 'e'),
            Position(3, 'c'),
            Position(5, 'c'),
            Position(5, 'g'),
            Position(7, 'c'),
            Position(7, 'e'),
            Position(7, 'g')
        )

        val possiblePositions = queen.possiblePositions(returnPieceIf(otherPieces::contains, Color.BLACK))

        assertPossiblePositionContains(possiblePositions, 3, 'c')
        assertPossiblePositionContains(possiblePositions, 3, 'e')
        assertPossiblePositionContains(possiblePositions, 3, 'g')
        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'e')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 5, 'c')
        assertPossiblePositionContains(possiblePositions, 5, 'd')
        assertPossiblePositionContains(possiblePositions, 5, 'f')
        assertPossiblePositionContains(possiblePositions, 5, 'g')
        assertPossiblePositionContains(possiblePositions, 6, 'd')
        assertPossiblePositionContains(possiblePositions, 6, 'e')
        assertPossiblePositionContains(possiblePositions, 6, 'f')
        assertPossiblePositionContains(possiblePositions, 7, 'c')
        assertPossiblePositionContains(possiblePositions, 7, 'e')
        assertPossiblePositionContains(possiblePositions, 7, 'g')

        assertEquals(16, possiblePositions.size)
    }

    @Test
    fun testFriendBoundary() {
        val queen = Queen(Color.WHITE, Position(5, 'e'))

        val otherPieces = listOf(
            Position(3, 'g'),
            Position(3, 'e'),
            Position(3, 'c'),
            Position(5, 'c'),
            Position(5, 'g'),
            Position(7, 'c'),
            Position(7, 'e'),
            Position(7, 'g')
        )

        val possiblePositions = queen.possiblePositions(returnPieceIf(otherPieces::contains, Color.WHITE))

        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'e')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 6, 'd')
        assertPossiblePositionContains(possiblePositions, 6, 'e')
        assertPossiblePositionContains(possiblePositions, 6, 'f')
        assertPossiblePositionContains(possiblePositions, 5, 'f')
        assertPossiblePositionContains(possiblePositions, 5, 'd')
        assertEquals(8, possiblePositions.size)
    }

    @Test
    fun testMoveUp() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        assertTrue(queen.moveTo(Position(1, 'e')))
        assertEquals(queen.position, Position(1, 'e'))
    }

    @Test
    fun testMoveDown() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        assertTrue(queen.moveTo(Position(8, 'e')))
        assertEquals(queen.position, Position(8, 'e'))
    }

    @Test
    fun testMoveLeft() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        assertTrue(queen.moveTo(Position(4, 'a')))
        assertEquals(queen.position, Position(4, 'a'))
    }

    @Test
    fun testMoveRight() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        assertTrue(queen.moveTo(Position(4, 'h')))
        assertEquals(queen.position, Position(4, 'h'))
    }

    @Test
    fun testMoveRightDown() {
        val queen = Queen(Color.WHITE, Position(5, 'e'))
        assertTrue(queen.moveTo(Position(2, 'h')))
        assertEquals(queen.position, Position(2, 'h'))
    }

    @Test
    fun testMoveLeftDown() {
        val queen = Queen(Color.WHITE, Position(5, 'e'))
        assertTrue(queen.moveTo(Position(1, 'a')))
        assertEquals(queen.position, Position(1, 'a'))
    }

    @Test
    fun testMoveRightUp() {
        val queen = Queen(Color.WHITE, Position(5, 'e'))
        assertTrue(queen.moveTo(Position(8, 'h')))
        assertEquals(queen.position, Position(8, 'h'))
    }

    @Test
    fun testMoveLeftUp() {
        val queen = Queen(Color.WHITE, Position(5, 'e'))
        assertTrue(queen.moveTo(Position(8, 'b')))
        assertEquals(queen.position, Position(8, 'b'))
    }

    @Test
    fun testNotMoveWeirdPosition() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        Assertions.assertFalse(queen.moveTo(Position(7, 'a')))
        assertEquals(queen.position, Position(4, 'e'))
    }

    @Test
    fun testEat() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        val to = Position(3, 'e')
        assertTrue(queen.moveTo(to, returnPieceIf(to::sameThat, Color.BLACK)))
        assertEquals(queen.position, to)
    }

    @Test
    fun testNotEatFriend() {
        val queen = Queen(Color.WHITE, Position(4, 'e'))
        val to = Position(3, 'e')
        Assertions.assertFalse(queen.moveTo(to, returnPieceIf(to::sameThat, Color.WHITE)))
        assertEquals(queen.position, Position(4, 'e'))
    }
}