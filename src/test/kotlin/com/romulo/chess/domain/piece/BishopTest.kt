package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
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


}