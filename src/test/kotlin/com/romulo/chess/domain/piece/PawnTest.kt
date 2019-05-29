package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Color.*
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PawnTest {

    @Test
    fun testWhiteCanNotWalkBackward() {
        val pawn = Pawn(
            WHITE,
            Position(3, 'a')
        )
        assertFalse(pawn.moveTo(Position(2, 'a')), "White pawn can not moveTo backward")

    }

    @Test
    fun testWhiteFirstWalkTwoSquares() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )
        assertTrue(pawn.moveTo(Position(4, 'a')), "White pawn can first moveTo two squares")
    }

    @Test
    fun testWhiteFirstWalkOneSquare() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )
        assertTrue(pawn.moveTo(Position(3, 'a')), "White pawn can first moveTo one square")
    }

    @Test
    fun testWhiteSecondWalkOneSquare() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )
        pawn.moveTo(Position(3, 'a'))
        assertTrue(pawn.moveTo(Position(4, 'a')), "White pawn can second moveTo one square")
    }

    @Test
    fun testWhiteSecondWalkTwoSquares() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )
        pawn.moveTo(Position(3, 'a'))
        assertFalse(pawn.moveTo(Position(5, 'a')), "White pawn can not second moveTo two squares")
    }

    @Test
    fun testWhitePawnCanNotMoveForwardRightInEmptyPosition() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )
        assertFalse(pawn.moveTo(Position(3, 'b')), "White pawn can not moveTo forward right")
    }

    @Test
    fun testWhitePawnCanNotMoveForwardLeftInEmptyPosition() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'b')
        )
        assertFalse(pawn.moveTo(Position(3, 'a')), "White pawn can not moveTo forward left")
    }

    @Test
    fun testWhitePawnMoveForwardRightInFullPosition() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )

        val to = Position(3, 'b')
        assertTrue(pawn.moveTo(to, returnPawnIf(to::sameThat, BLACK)), "White pawn can moveTo forward right")
    }

    @Test
    fun testWhitePawnCannotMoveForwardRightInFullWhitePosition() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )

        val to = Position(3, 'b')
        assertFalse(pawn.moveTo(to, returnPawnIf(to::sameThat, WHITE)), "White pawn can not moveTo forward right")
    }

    @Test
    fun testWhitePawnCanNotMoveForwardLeftInFullPosition() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'b')
        )

        val to = Position(3, 'a')
        assertTrue(pawn.moveTo(to, returnPawnIf(to::sameThat, BLACK)), "White pawn can not moveTo forward left")
    }

    @Test
    fun testPawnCanNotSidewalk() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'a')
        )
        assertFalse(pawn.moveTo(Position(2, 'b')), "Pawn can not sidewalk")
    }

    @Test
    fun testBlackCanNotWalkForward() {
        val pawn = Pawn(
            BLACK,
            Position(7, 'a')
        )
        assertFalse(pawn.moveTo(Position(8, 'a')), "Black pawn can not moveTo forward")
    }

    @Test
    fun testBlackFirstWalkTwoSquares() {
        val pawn = Pawn(
            BLACK,
            Position(7, 'a')
        )
        assertTrue(pawn.moveTo(Position(5, 'a')), "Black pawn can first moveTo two squares")
    }

    @Test
    fun testBlackFirstWalkOneSquare() {
        val pawn = Pawn(
            BLACK,
            Position(7, 'a')
        )
        assertTrue(pawn.moveTo(Position(6, 'a')), "Black pawn can first moveTo one square")
    }

    @Test
    fun testBlackSecondWalkOneSquare() {
        val pawn = Pawn(
            BLACK,
            Position(7, 'a')
        )
        pawn.moveTo(Position(6, 'a'))
        assertTrue(pawn.moveTo(Position(5, 'a')), "White pawn can second moveTo one square")
    }

    @Test
    fun testBlackSecondWalkTwoSquares() {
        val pawn = Pawn(
            BLACK,
            Position(7, 'a')
        )
        pawn.moveTo(Position(6, 'a'))
        assertFalse(pawn.moveTo(Position(4, 'a')), "Black pawn can not second moveTo two squares")
    }

    @Test
    fun testBlackPawnCanNotMoveBackwardRightInEmptyPosition() {
        val pawn = Pawn(
            BLACK,
            Position(2, 'b')
        )
        assertFalse(pawn.moveTo(Position(1, 'a')), "Black pawn can not moveTo backward right")
    }

    @Test
    fun testBlackPawnCanNotMoveBackwardLeftInEmptyPosition() {
        val pawn = Pawn(
            BLACK,
            Position(2, 'b')
        )
        assertFalse(pawn.moveTo(Position(1, 'c')), "Black pawn can not moveTo backward left")
    }

    @Test
    fun testBlackPawnCannotMoveBackwardRightInFullBlackPosition() {
        val pawn = Pawn(
            BLACK,
            Position(2, 'b')
        )

        val to = Position(1, 'a')
        assertFalse(pawn.moveTo(to, returnPawnIf(to::sameThat, BLACK)), "Black pawn cannot moveTo backward black right")
    }

    @Test
    fun testBlackPawnCanMoveBackwardRightInFullPosition() {
        val pawn = Pawn(
            BLACK,
            Position(2, 'b')
        )

        val to = Position(1, 'a')
        assertTrue(pawn.moveTo(to, returnPawnIf(to::sameThat, WHITE)), "Black pawn can moveTo backward right")
    }

    @Test
    fun testBlackPawnCanMoveBackwardLeftInFullPosition() {
        val pawn = Pawn(
            BLACK,
            Position(2, 'b')
        )
        val to = Position(1, 'c')
        assertTrue(pawn.moveTo(to, returnPawnIf(to::sameThat, WHITE)), "Black pawn can moveTo backward left")
    }

    @Test
    fun testPossiblePositionsForInitialWhitePawn() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'b')
        )

        val possiblePositions: List<Position> = pawn.possiblePositions { null }

        assertEquals(2, possiblePositions.size)
        assertTrue(possiblePositions.contains(Position(3, 'b')))
        assertTrue(possiblePositions.contains(Position(4, 'b')))
    }

    @Test
    fun testPossiblePositionsForWhitePawn() {
        val pawn = Pawn(
            WHITE,
            Position(2, 'b')
        )

        pawn.moveTo(Position(4, 'b'))

        val possiblePositions: List<Position> = pawn.possiblePositions(
            returnPawnIf({ p -> p.letter == 'a' || p.letter == 'c' }, BLACK)
        )

        assertEquals(3, possiblePositions.size)
        assertTrue(possiblePositions.contains(Position(5, 'b')))
        assertTrue(possiblePositions.contains(Position(5, 'c')))
        assertTrue(possiblePositions.contains(Position(5, 'a')))
    }

    @Test
    fun testPossiblePositionsForInitialBlackPawn() {
        val pawn = Pawn(
            BLACK,
            Position(7, 'b')
        )

        val possiblePositions: List<Position> = pawn.possiblePositions { null }

        assertEquals(2, possiblePositions.size)
        assertTrue(possiblePositions.contains(Position(6, 'b')))
        assertTrue(possiblePositions.contains(Position(5, 'b')))
    }
}