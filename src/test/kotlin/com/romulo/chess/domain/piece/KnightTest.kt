package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class KnightTest {

    @Test
    fun testAllPossibilities() {
        val knight = Knight(Color.WHITE, Position(5, 'e'))

        val possiblePositions = knight.possiblePositions { null }

        assertPossiblePositionContains(possiblePositions, 3, 'd')
        assertPossiblePositionContains(possiblePositions, 3, 'f')
        assertPossiblePositionContains(possiblePositions, 4, 'c')
        assertPossiblePositionContains(possiblePositions, 6, 'c')
        assertPossiblePositionContains(possiblePositions, 7, 'd')
        assertPossiblePositionContains(possiblePositions, 7, 'f')
        assertPossiblePositionContains(possiblePositions, 4, 'g')
        assertPossiblePositionContains(possiblePositions, 6, 'g')
        assertEquals(8, possiblePositions.size)
    }

    @Test
    fun testPossibilitiesOnEdge() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))

        val possiblePositions = knight.possiblePositions { null }

        assertPossiblePositionContains(possiblePositions, 2, 'f')
        assertPossiblePositionContains(possiblePositions, 3, 'g')
        assertEquals(2, possiblePositions.size)
    }

    @Test
    fun testEatScenario() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))

        val possiblePositions = knight.possiblePositions(returnPieceIf(Position(3, 'g')::sameThat, Color.BLACK))

        assertPossiblePositionContains(possiblePositions, 2, 'f')
        assertPossiblePositionContains(possiblePositions, 3, 'g')
        assertEquals(2, possiblePositions.size)
    }

    @Test
    fun testFriendTargetScenario() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))

        val possiblePositions = knight.possiblePositions(returnPieceIf(Position(3, 'g')::sameThat, Color.WHITE))

        assertPossiblePositionContains(possiblePositions, 2, 'f')
        assertEquals(1, possiblePositions.size)
    }

    @Test
    fun testMoveTo() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))

        assertTrue(knight.moveTo(Position(2, 'f')))
        assertEquals(knight.position, Position(2, 'f'))
    }

    @Test
    fun testEat() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))
        val to = Position(2, 'f')

        assertTrue(knight.moveTo(to, returnPieceIf(to::sameThat, Color.BLACK)))
        assertEquals(knight.position, to)
    }

    @Test
    fun testNotEatFriend() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))
        val to = Position(2, 'f')

        assertFalse(knight.moveTo(to, returnPieceIf(to::sameThat, Color.WHITE)))
        assertEquals(knight.position, Position(1, 'h'))
    }

    @Test
    fun testNotMoveToWeirdPosition() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))
        val to = Position(4, 'a')

        assertFalse(knight.moveTo(to, returnPieceIf(to::sameThat, Color.WHITE)))
        assertEquals(knight.position, Position(1, 'h'))
    }

    @Test
    fun testJumpPieces() {
        val knight = Knight(Color.WHITE, Position(1, 'h'))
        val to = Position(3, 'g')

        assertTrue(knight.moveTo(to, returnPieceIf({ (number) -> number == 2 }, Color.WHITE)))
        assertEquals(knight.position, to)
    }

}