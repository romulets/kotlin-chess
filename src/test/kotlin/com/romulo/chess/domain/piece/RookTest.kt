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

        assertEquals(14, possiblePositions.size)
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
    }

}