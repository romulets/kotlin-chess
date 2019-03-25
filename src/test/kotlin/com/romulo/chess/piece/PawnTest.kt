package com.romulo.chess.piece

import com.romulo.chess.Position
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PawnTest {

    @Test
    fun testWhiteCanNotWalkBackward() {
        val pawn = Pawn(Color.WHITE, Position(3, 'a'))
        assertFalse("White pawn can not move backward", pawn.canWalk(Position(2, 'a')))
    }

    @Test
    fun testWhiteFirstWalkTwoSquares() {
        val pawn = Pawn(Color.WHITE, Position(2, 'a'))
        assertTrue("White pawn can first move two squares", pawn.canWalk(Position(4, 'a')))
    }

    @Test
    fun testWhiteFirstWalkOneSquare() {
        val pawn = Pawn(Color.WHITE, Position(2, 'a'))
        assertTrue("White pawn can first move one square", pawn.canWalk(Position(3, 'a')))
    }

    @Test
    fun testWhiteSecondWalkOneSquare() {
        val pawn = Pawn(Color.WHITE, Position(2, 'a'))
        pawn.move(Position(3, 'a'))
        assertTrue("White pawn can second move one square", pawn.canWalk(Position(4, 'a')))
    }

    @Test
    fun testWhiteSecondWalkTwoSquares() {
        val pawn = Pawn(Color.WHITE, Position(2, 'a'))
        pawn.move(Position(3, 'a'))
        assertFalse("White pawn can not second move two squares", pawn.canWalk(Position(5, 'a')))
    }

    @Test
    fun testPawnCanNotSidewalk() {
        val pawn = Pawn(Color.WHITE, Position(2, 'a'))
        assertFalse("White pawn can not second move two squares", pawn.canWalk(Position(2, 'b')))
    }

    @Test
    fun testBlackCanNotWalkForward() {
        val pawn = Pawn(Color.BLACK, Position(7, 'a'))
        assertFalse("Black pawn can not move forward", pawn.canWalk(Position(8, 'a')))
    }

    @Test
    fun testBlackFirstWalkTwoSquares() {
        val pawn = Pawn(Color.BLACK, Position(7, 'a'))
        assertTrue("Black pawn can first move two squares", pawn.canWalk(Position(5, 'a')))
    }

    @Test
    fun testBlackFirstWalkOneSquare() {
        val pawn = Pawn(Color.BLACK, Position(7, 'a'))
        assertTrue("Black pawn can first move one square", pawn.canWalk(Position(6, 'a')))
    }

    @Test
    fun testBlackSecondWalkOneSquare() {
        val pawn = Pawn(Color.BLACK, Position(7, 'a'))
        pawn.move(Position(6, 'a'))
        assertTrue("White pawn can second move one square", pawn.canWalk(Position(5, 'a')))
    }

    @Test
    fun testBlackSecondWalkTwoSquares() {
        val pawn = Pawn(Color.BLACK, Position(7, 'a'))
        pawn.move(Position(6, 'a'))
        assertFalse("White pawn can not second move two squares", pawn.canWalk(Position(4, 'a')))
    }

}