package com.romulo.chess.piece

import com.romulo.chess.Position
import com.romulo.chess.position
import com.romulo.chess.fullPosition
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PawnTest {

    @Test
    fun testWhiteCanNotWalkBackward() {
        val pawn = Pawn(Color.WHITE, fullPosition(3, 'a'))
        assertFalse("White pawn can not moveTo backward", pawn.moveTo(position(2, 'a')))
    }

    @Test
    fun testWhiteFirstWalkTwoSquares() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'a'))
        assertTrue("White pawn can first moveTo two squares", pawn.moveTo(position(4, 'a')))
    }

    @Test
    fun testWhiteFirstWalkOneSquare() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'a'))
        assertTrue("White pawn can first moveTo one square", pawn.moveTo(position(3, 'a')))
    }

    @Test
    fun testWhiteSecondWalkOneSquare() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'a'))
        pawn.moveTo(Position(3, 'a'))
        assertTrue("White pawn can second moveTo one square", pawn.moveTo(position(4, 'a')))
    }

    @Test
    fun testWhiteSecondWalkTwoSquares() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'a'))
        pawn.moveTo(Position(3, 'a'))
        assertFalse("White pawn can not second moveTo two squares", pawn.moveTo(position(5, 'a')))
    }

    @Test
    fun testWhitePawnCanNotMoveForwardRightInEmptyPosition() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'a'))
        assertFalse("White pawn can not moveTo forward right", pawn.moveTo(position(3, 'b')))
    }

    @Test
    fun testWhitePawnCanNotMoveForwardLeftInEmptyPosition() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'b'))
        assertFalse("White pawn can not moveTo forward left", pawn.moveTo(position(3, 'a')))
    }

    @Test
    fun testWhitePawnMoveForwardRightInFullPosition() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'a'))
        assertTrue("White pawn can not moveTo forward right", pawn.moveTo(fullPosition(3, 'b')))
    }

    @Test
    fun testWhitePawnCanNotMoveForwardLeftInFullPosition() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'b'))
        assertTrue("White pawn can not moveTo forward left", pawn.moveTo(fullPosition(3, 'a')))
    }

    @Test
    fun testPawnCanNotSidewalk() {
        val pawn = Pawn(Color.WHITE, fullPosition(2, 'a'))
        assertFalse("Pawn can not sidewalk", pawn.moveTo(position(2, 'b')))
    }

    @Test
    fun testBlackCanNotWalkForward() {
        val pawn = Pawn(Color.BLACK, fullPosition(7, 'a'))
        assertFalse("Black pawn can not moveTo forward", pawn.moveTo(position(8, 'a')))
    }

    @Test
    fun testBlackFirstWalkTwoSquares() {
        val pawn = Pawn(Color.BLACK, fullPosition(7, 'a'))
        assertTrue("Black pawn can first moveTo two squares", pawn.moveTo(position(5, 'a')))
    }

    @Test
    fun testBlackFirstWalkOneSquare() {
        val pawn = Pawn(Color.BLACK, fullPosition(7, 'a'))
        assertTrue("Black pawn can first moveTo one square", pawn.moveTo(position(6, 'a')))
    }

    @Test
    fun testBlackSecondWalkOneSquare() {
        val pawn = Pawn(Color.BLACK, fullPosition(7, 'a'))
        pawn.moveTo(Position(6, 'a'))
        assertTrue("White pawn can second moveTo one square", pawn.moveTo(position(5, 'a')))
    }

    @Test
    fun testBlackSecondWalkTwoSquares() {
        val pawn = Pawn(Color.BLACK, fullPosition(7, 'a'))
        pawn.moveTo(Position(6, 'a'))
        assertFalse("Black pawn can not second moveTo two squares", pawn.moveTo(position(4, 'a')))
    }

    @Test
    fun testBlackPawnCanNotMoveBackwardRightInEmptyPosition() {
        val pawn = Pawn(Color.BLACK, fullPosition(2, 'b'))
        assertFalse("Black pawn can not moveTo backward right", pawn.moveTo(position(1, 'a')))
    }

    @Test
    fun testBlackPawnCanNotMoveBackwardLeftInEmptyPosition() {
        val pawn = Pawn(Color.BLACK, fullPosition(2, 'b'))
        assertFalse("Black pawn can not moveTo backward left", pawn.moveTo(position(1, 'c')))
    }

    @Test
    fun testBlackPawnCanMoveBackwardRightInFullPosition() {
        val pawn = Pawn(Color.BLACK, fullPosition(2, 'b'))
        assertTrue("Black pawn can moveTo backward right", pawn.moveTo(fullPosition(1, 'a')))
    }

    @Test
    fun testBlackPawnCanMoveBackwardLeftInFullPosition() {
        val pawn = Pawn(Color.BLACK, fullPosition(2, 'b'))
        assertTrue("Black pawn can moveTo backward left", pawn.moveTo(fullPosition(1, 'c')))
    }
}