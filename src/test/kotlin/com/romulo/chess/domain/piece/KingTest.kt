package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class KingTest {


    @Test
    fun testAllPossibilities() {
        val king = King(Color.WHITE, Position(5, 'e'))

        val possiblePositions = king.possiblePositions { null }

        assertPossiblePositionContains(possiblePositions, 4, 'd')
        assertPossiblePositionContains(possiblePositions, 4, 'e')
        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 5, 'd')
        assertPossiblePositionContains(possiblePositions, 5, 'f')
        assertPossiblePositionContains(possiblePositions, 6, 'd')
        assertPossiblePositionContains(possiblePositions, 6, 'e')
        assertPossiblePositionContains(possiblePositions, 6, 'f')
        assertEquals(8, possiblePositions.size)
    }

    @Test
    fun testPossibilitiesOnTheEdge() {
        val king = King(Color.WHITE, Position(8, 'h'))

        val possiblePositions = king.possiblePositions { null }

        assertPossiblePositionContains(possiblePositions, 7, 'h')
        assertPossiblePositionContains(possiblePositions, 7, 'g')
        assertPossiblePositionContains(possiblePositions, 8, 'g')
        assertEquals(3, possiblePositions.size)
    }

    @Test
    fun testPossibilitiesWhenOpponentPiece() {
        val king = King(Color.WHITE, Position(8, 'h'))

        val possiblePositions = king.possiblePositions(returnPieceIf(Position(7, 'h')::sameThat, Color.BLACK))

        assertPossiblePositionContains(possiblePositions, 7, 'h')
        assertPossiblePositionContains(possiblePositions, 7, 'g')
        assertPossiblePositionContains(possiblePositions, 8, 'g')
        assertEquals(3, possiblePositions.size)
    }

    @Test
    fun testPossibilitiesWhenFriendPiece() {
        val king = King(Color.WHITE, Position(8, 'h'))

        val possiblePositions = king.possiblePositions(returnPieceIf(Position(7, 'h')::sameThat, Color.WHITE))

        assertPossiblePositionContains(possiblePositions, 7, 'g')
        assertPossiblePositionContains(possiblePositions, 8, 'g')
        assertEquals(2, possiblePositions.size)
    }

}