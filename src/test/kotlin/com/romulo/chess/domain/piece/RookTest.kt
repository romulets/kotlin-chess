package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RookTest {

    @Test
    fun testAllSidePossibilities() {
        val rook = Rook(Color.WHITE, Position(4, 'e'))
        val possiblePositions = rook.possiblePositions { null }

        assertTrue(possiblePositions.contains(Position(1, 'e')))
        assertTrue(possiblePositions.contains(Position(2, 'e')))
        assertTrue(possiblePositions.contains(Position(3, 'e')))
        assertTrue(possiblePositions.contains(Position(5, 'e')))
        assertTrue(possiblePositions.contains(Position(6, 'e')))
        assertTrue(possiblePositions.contains(Position(7, 'e')))
        assertTrue(possiblePositions.contains(Position(8, 'e')))
        assertTrue(possiblePositions.contains(Position(4, 'a')))
        assertTrue(possiblePositions.contains(Position(4, 'b')))
        assertTrue(possiblePositions.contains(Position(4, 'c')))
        assertTrue(possiblePositions.contains(Position(4, 'd')))
        assertTrue(possiblePositions.contains(Position(4, 'f')))
        assertTrue(possiblePositions.contains(Position(4, 'g')))
        assertTrue(possiblePositions.contains(Position(4, 'h')))
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

        assertTrue(possiblePositions.contains(Position(2, 'e')))
        assertTrue(possiblePositions.contains(Position(3, 'e')))
        assertTrue(possiblePositions.contains(Position(5, 'e')))
        assertTrue(possiblePositions.contains(Position(6, 'e')))
        assertTrue(possiblePositions.contains(Position(4, 'c')))
        assertTrue(possiblePositions.contains(Position(4, 'd')))
        assertTrue(possiblePositions.contains(Position(4, 'f')))
        assertTrue(possiblePositions.contains(Position(4, 'g')))
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

        assertTrue(possiblePositions.contains(Position(3, 'e')))
        assertTrue(possiblePositions.contains(Position(5, 'e')))
        assertTrue(possiblePositions.contains(Position(4, 'd')))
        assertTrue(possiblePositions.contains(Position(4, 'f')))
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