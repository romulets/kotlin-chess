package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class BishopTest {

    @Test
    fun testAllSidePossibilities() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))

        val possiblePositions = bishop.possiblePositions { null }

        assertPossiblePositionContains(possiblePositions, 8, 'b')
        assertPossiblePositionContains(possiblePositions, 7, 'c')
        assertPossiblePositionContains(possiblePositions, 6, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 3, 'g')
        assertPossiblePositionContains(possiblePositions, 2, 'h')
        assertPossiblePositionContains(possiblePositions, 1, 'a')
        assertPossiblePositionContains(possiblePositions, 2, 'b')
        assertPossiblePositionContains(possiblePositions, 3, 'c')
        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 6, 'f')
        assertPossiblePositionContains(possiblePositions, 7, 'g')
        assertPossiblePositionContains(possiblePositions, 8, 'h')
        assertEquals(13, possiblePositions.size)
    }

    @Test
    fun testEatPossibilities() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))

        val otherPieces = listOf(
            Position(3, 'g'),
            Position(3, 'c'),
            Position(7, 'c'),
            Position(7, 'g')
        )

        val possiblePositions = bishop.possiblePositions(returnPieceIf(otherPieces::contains, Color.BLACK))

        assertPossiblePositionContains(possiblePositions, 7, 'c')
        assertPossiblePositionContains(possiblePositions, 6, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 3, 'g')
        assertPossiblePositionContains(possiblePositions, 3, 'c')
        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 6, 'f')
        assertPossiblePositionContains(possiblePositions, 7, 'g')
        assertEquals(8, possiblePositions.size)
    }

    @Test
    fun testFriendBoundary() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))

        val otherPieces = listOf(
            Position(3, 'g'),
            Position(3, 'c'),
            Position(7, 'c'),
            Position(7, 'g')
        )

        val possiblePositions = bishop.possiblePositions(returnPieceIf(otherPieces::contains, Color.WHITE))

        assertPossiblePositionContains(possiblePositions, 6, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 6, 'f')
        assertEquals(4, possiblePositions.size)
    }

    @Test
    fun testMoveRightDown() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))
        assertTrue(bishop.moveTo(Position(2, 'h')))
        assertEquals(bishop.position, Position(2, 'h'))
    }

    @Test
    fun testMoveLeftDown() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))
        assertTrue(bishop.moveTo(Position(1, 'a')))
        assertEquals(bishop.position, Position(1, 'a'))
    }

    @Test
    fun testMoveRightUp() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))
        assertTrue(bishop.moveTo(Position(8, 'h')))
        assertEquals(bishop.position, Position(8, 'h'))
    }

    @Test
    fun testMoveLeftUp() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))
        assertTrue(bishop.moveTo(Position(8, 'b')))
        assertEquals(bishop.position, Position(8, 'b'))
    }

    @Test
    fun testNotMoveToRandomPlace() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))
        assertFalse(bishop.moveTo(Position(1, 'h')))
        assertEquals(bishop.position, Position(5, 'e'))
    }

    @Test
    fun testEat() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))
        val to = Position(3, 'g')
        assertTrue(bishop.moveTo(to, returnPieceIf(to::sameThat, Color.BLACK)))
        assertEquals(bishop.position, to)
    }

    @Test
    fun testNotEatFriend() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))
        val to = Position(3, 'g')
        assertFalse(bishop.moveTo(to, returnPieceIf(to::sameThat, Color.WHITE)))
        assertEquals(bishop.position, Position(5, 'e'))
    }

}