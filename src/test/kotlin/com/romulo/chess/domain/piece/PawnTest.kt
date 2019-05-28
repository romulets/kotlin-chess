package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import com.romulo.chess.domain.position
import com.romulo.chess.domain.fullPosition
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PawnTest {

    @Test
    fun testWhiteCanNotWalkBackward() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(3, 'a')
        )
        assertFalse(pawn.moveTo(position(2, 'a')), "White pawn can not moveTo backward")

    }

    @Test
    fun testWhiteFirstWalkTwoSquares() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'a')
        )
        assertTrue(pawn.moveTo(position(4, 'a')), "White pawn can first moveTo two squares")
    }

    @Test
    fun testWhiteFirstWalkOneSquare() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'a')
        )
        assertTrue(pawn.moveTo(position(3, 'a')), "White pawn can first moveTo one square")
    }

    @Test
    fun testWhiteSecondWalkOneSquare() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'a')
        )
        pawn.moveTo(Position(3, 'a'))
        assertTrue(pawn.moveTo(position(4, 'a')), "White pawn can second moveTo one square")
    }

    @Test
    fun testWhiteSecondWalkTwoSquares() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'a')
        )
        pawn.moveTo(Position(3, 'a'))
        assertFalse(pawn.moveTo(position(5, 'a')), "White pawn can not second moveTo two squares")
    }

    @Test
    fun testWhitePawnCanNotMoveForwardRightInEmptyPosition() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'a')
        )
        assertFalse(pawn.moveTo(position(3, 'b')), "White pawn can not moveTo forward right")
    }

    @Test
    fun testWhitePawnCanNotMoveForwardLeftInEmptyPosition() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'b')
        )
        assertFalse(pawn.moveTo(position(3, 'a')), "White pawn can not moveTo forward left")
    }

    @Test
    fun testWhitePawnMoveForwardRightInFullPosition() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'a')
        )
        assertTrue(pawn.moveTo(fullPosition(3, 'b')), "White pawn can not moveTo forward right")
    }

    @Test
    fun testWhitePawnCanNotMoveForwardLeftInFullPosition() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'b')
        )
        assertTrue(pawn.moveTo(fullPosition(3, 'a')), "White pawn can not moveTo forward left")
    }

    @Test
    fun testPawnCanNotSidewalk() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'a')
        )
        assertFalse(pawn.moveTo(position(2, 'b')), "Pawn can not sidewalk")
    }

    @Test
    fun testBlackCanNotWalkForward() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(7, 'a')
        )
        assertFalse(pawn.moveTo(position(8, 'a')), "Black pawn can not moveTo forward")
    }

    @Test
    fun testBlackFirstWalkTwoSquares() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(7, 'a')
        )
        assertTrue(pawn.moveTo(position(5, 'a')), "Black pawn can first moveTo two squares")
    }

    @Test
    fun testBlackFirstWalkOneSquare() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(7, 'a')
        )
        assertTrue(pawn.moveTo(position(6, 'a')), "Black pawn can first moveTo one square")
    }

    @Test
    fun testBlackSecondWalkOneSquare() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(7, 'a')
        )
        pawn.moveTo(Position(6, 'a'))
        assertTrue(pawn.moveTo(position(5, 'a')), "White pawn can second moveTo one square")
    }

    @Test
    fun testBlackSecondWalkTwoSquares() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(7, 'a')
        )
        pawn.moveTo(Position(6, 'a'))
        assertFalse(pawn.moveTo(position(4, 'a')), "Black pawn can not second moveTo two squares")
    }

    @Test
    fun testBlackPawnCanNotMoveBackwardRightInEmptyPosition() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(2, 'b')
        )
        assertFalse(pawn.moveTo(position(1, 'a')), "Black pawn can not moveTo backward right")
    }

    @Test
    fun testBlackPawnCanNotMoveBackwardLeftInEmptyPosition() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(2, 'b')
        )
        assertFalse(pawn.moveTo(position(1, 'c')), "Black pawn can not moveTo backward left")
    }

    @Test
    fun testBlackPawnCanMoveBackwardRightInFullPosition() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(2, 'b')
        )
        assertTrue(pawn.moveTo(fullPosition(1, 'a')), "Black pawn can moveTo backward right")
    }

    @Test
    fun testBlackPawnCanMoveBackwardLeftInFullPosition() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(2, 'b')
        )
        assertTrue(pawn.moveTo(fullPosition(1, 'c')), "Black pawn can moveTo backward left")
    }

    @Test
    fun testPossiblePositionsForInitialWhitePawn() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'b')
        )

        val possiblePositions: List<Position> = pawn.possiblePositions { false }

        assertEquals(2, possiblePositions.size)
        assertTrue(possiblePositions.contains(position(3, 'b')))
        assertTrue(possiblePositions.contains(position(4, 'b')))
    }

    @Test
    fun testPossiblePositionsForWhitePawn() {
        val pawn = Pawn(
            Color.WHITE,
            fullPosition(2, 'b')
        )

        pawn.moveTo(position(4, 'b'))

        val possiblePositions: List<Position> = pawn.possiblePositions { p ->
            p.letter == 'a' || p.letter == 'c'
        }

        assertEquals(3, possiblePositions.size)
        assertTrue(possiblePositions.contains(position(5, 'b')))
        assertTrue(possiblePositions.contains(fullPosition(5, 'c')))
        assertTrue(possiblePositions.contains(fullPosition(5, 'a')))
    }

    @Test
    fun testPossiblePositionsForInitialBlackPawn() {
        val pawn = Pawn(
            Color.BLACK,
            fullPosition(7, 'b')
        )

        val possiblePositions: List<Position> = pawn.possiblePositions { false }

        assertEquals(2, possiblePositions.size)
        assertTrue(possiblePositions.contains(position(6, 'b')))
        assertTrue(possiblePositions.contains(position(5, 'b')))
    }
}