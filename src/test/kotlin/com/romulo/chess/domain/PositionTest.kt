package com.romulo.chess.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun testValidposition() {
        val pos = position(1, 'a')
        assertEquals(1, pos.number)
        assertEquals('a', pos.letter)
    }

    @Test
    fun testInvalidRow() {
        assertThrows(IllegalArgumentException::class.java) {
            position(0, 'g')
        }
    }

    @Test
    fun testInvalidLetter() {
        assertThrows(IllegalArgumentException::class.java) {
            position(0, 'i')
        }
    }

    @Test
    fun testIsStraightForward() {
        val pos = position(1, 'a')
        assertTrue(pos.isStraightForward(position(2, 'a')), "1a is straight forward to 2a")
    }

    @Test
    fun testIsNotStraightForward() {
        val pos = position(2, 'a')
        assertFalse(pos.isStraightForward(position(1, 'a')), "2a is not straight forward to 1a")
    }

    @Test
    fun testIsStraightBackwardValid() {
        val pos = position(2, 'a')
        assertTrue(pos.isStraightBackward(position(1, 'a')), "1a is straight backward to 2a")
    }

    @Test
    fun testIsNotStraightBackward() {
        val pos = position(1, 'a')
        assertFalse(pos.isStraightBackward(position(2, 'a')), "2a is not straight backward to 1a")
    }

    @Test
    fun testIsForwardDiagonalRight() {
        val pos = position(1, 'a')
        assertTrue(pos.isForwardDiagonal(position(2, 'b')), "1a is forward right to 2b")
    }

    @Test
    fun testIsNotForwardDiagonalRightInvalidDiagonal() {
        val pos = position(1, 'a')
        assertFalse(pos.isForwardDiagonal(position(4, 'h')), "1b is not forward right to 4h")
    }

    @Test
    fun testIsNotForwardDiagonalRightBackwardPosition() {
        val pos = position(2, 'b')
        assertFalse(pos.isForwardDiagonal(position(1, 'a')), "2b is not forward right to 1a")
    }

    @Test
    fun testIsForwardDiagonalLeft() {
        val pos = position(1, 'b')
        assertTrue(pos.isForwardDiagonal(position(2, 'a')), "1b is forward left to 2a")
    }

    @Test
    fun testIsNotForwardDiagonalLeftInvalidDiagonal() {
        val pos = position(2, 'b')
        assertFalse(pos.isForwardDiagonal(position(4, 'a')), "2b is not forward left to 4a")
    }

    @Test
    fun testIsNotForwardDiagonalLeftBackwardPosition() {
        val pos = position(2, 'b')
        assertFalse(pos.isForwardDiagonal(position(1, 'c')), "2b is not forward left to 1c")
    }

    @Test
    fun testIsBackwardDiagonalRight() {
        val pos = position(2, 'b')
        assertTrue(pos.isBackwardDiagonal(position(1, 'c')), "2b is backward right to 1c")
    }

    @Test
    fun testIsNotBackwardDiagonalRightForwardPosition() {
        val pos = position(2, 'b')
        assertFalse(pos.isBackwardDiagonal(position(3, 'c')), "2b is not backward right to 3c")
    }

    @Test
    fun testIsBackwardDiagonalLeft() {
        val pos = position(2, 'b')
        assertTrue(pos.isBackwardDiagonal(position(1, 'a')), "2b is backward left to 1a")
    }

    @Test
    fun testIsNotBackwardDiagonalLeftForwardPosition() {
        val pos = position(2, 'b')
        assertFalse(pos.isBackwardDiagonal(position(3, 'a')), "2b is not forward left to 3a")
    }

    @Test
    fun testDistanceToStraightForward() {
        val pos1 = position(1, 'a')
        val pos2 = position(8, 'a')
        assertEquals(7, pos1.distanceTo(pos2))
    }

    @Test
    fun testDistanceToBackward() {
        val pos1 = position(8, 'a')
        val pos2 = position(1, 'a')
        assertEquals(7, pos1.distanceTo(pos2))
    }

    @Test
    fun testDistanceToDiagonal() {
        val pos1 = position(8, 'a')
        val pos2 = position(1, 'h')
        assertEquals(7, pos1.distanceTo(pos2))
    }

}