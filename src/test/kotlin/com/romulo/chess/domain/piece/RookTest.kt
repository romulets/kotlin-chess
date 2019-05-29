package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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

        val possiblePositions = rook.possiblePositions(returnPawnIf(otherPieces::contains, Color.BLACK))

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

        val possiblePositions = rook.possiblePositions(returnPawnIf(otherPieces::contains, Color.WHITE))

        assertTrue(possiblePositions.contains(Position(3, 'e')))
        assertTrue(possiblePositions.contains(Position(5, 'e')))
        assertTrue(possiblePositions.contains(Position(4, 'd')))
        assertTrue(possiblePositions.contains(Position(4, 'f')))
        assertEquals(4, possiblePositions.size)
    }

}