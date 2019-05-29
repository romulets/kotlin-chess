package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class BishopTest {

    @Test
    @Disabled
    fun testAllSidePossibilities() {
        val bishop = Bishop(Color.WHITE, Position(5, 'e'))

        val possiblePositions = bishop.possiblePositions { null }

        assertPossiblePositionContains(possiblePositions, 4, 'f')
        assertPossiblePositionContains(possiblePositions, 3, 'g')
        assertPossiblePositionContains(possiblePositions, 2, 'h')
        assertEquals(3, possiblePositions.size)
    }

}