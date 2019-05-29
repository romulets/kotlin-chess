package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class RookTest {

    @Test
    fun testAllSidePossibilities() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        val possiblePositions = rook.possiblePositions { null }

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
        assertEquals(14, possiblePositions.size)
    }

    @Test
    fun testEatPosition() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))

        val otherPieces = listOf(
            Position(2, 'e'),
            Position(4, 'c'),
            Position(6, 'e'),
            Position(4, 'g')
        )

        val possiblePositions = rook.possiblePositions(returnPieceIf(otherPieces::contains, Color.BLACK))

        assertPossiblePositionContains(possiblePositions, 2, 'e')
        assertPossiblePositionContains(possiblePositions, 3, 'e')
        assertPossiblePositionContains(possiblePositions, 5, 'e')
        assertPossiblePositionContains(possiblePositions, 6, 'e')
        assertPossiblePositionContains(possiblePositions, 4, 'c')
        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 4, 'g')
        assertEquals(8, possiblePositions.size)
    }

    @Test
    fun testFriendsPosition() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))

        val otherPieces = listOf(
            Position(2, 'e'),
            Position(4, 'c'),
            Position(6, 'e'),
            Position(4, 'g')
        )

        val possiblePositions = rook.possiblePositions(returnPieceIf(otherPieces::contains, Color.WHITE))

        assertPossiblePositionContains(possiblePositions, 3, 'e')
        assertPossiblePositionContains(possiblePositions, 5, 'e')
        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertEquals(4, possiblePositions.size)
    }

    @Test
    fun testMoveUp() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        assertTrue(rook.moveTo(Position(1, 'e')))
        assertEquals(rook.position, Position(1, 'e'))
    }

    @Test
    fun testMoveDown() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        assertTrue(rook.moveTo(Position(8, 'e')))
        assertEquals(rook.position, Position(8, 'e'))
    }

    @Test
    fun testMoveLeft() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        assertTrue(rook.moveTo(Position(4, 'a')))
        assertEquals(rook.position, Position(4, 'a'))
    }

    @Test
    fun testMoveRight() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        assertTrue(rook.moveTo(Position(4, 'h')))
        assertEquals(rook.position, Position(4, 'h'))
    }

    @Test
    fun testNotMoveWeirdPosition() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        assertFalse(rook.moveTo(Position(7, 'a')))
        assertEquals(rook.position, Position(4, 'e'))
    }

    @Test
    fun testEat() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        val to = Position(3, 'e')
        assertTrue(rook.moveTo(to, returnPieceIf(to::sameThat, Color.BLACK)))
        assertEquals(rook.position, to)
    }

    @Test
    fun testNotEatFriend() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        val to = Position(3, 'e')
        assertFalse(rook.moveTo(to, returnPieceIf(to::sameThat, Color.WHITE)))
        assertEquals(rook.position, Position(4, 'e'))
    }

}