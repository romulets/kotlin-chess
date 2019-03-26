package com.romulo.chess

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class PositionTest {

    @Test
    fun testValidposition() {
        val pos = position(1, 'a')
        Assert.assertEquals(1, pos.number)
        Assert.assertEquals('a', pos.letter)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidRow() {
        position(0, 'g')
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidLetter() {
        position(0, 'i')
    }

    @Test
    fun testIsStraightForward() {
        val pos = position(1, 'a')
        assertTrue("1a is straight forward to 2a", pos.isStraightForward(position(2, 'a')))
    }

    @Test
    fun testIsNotStraightForward() {
        val pos = position(2, 'a')
        assertFalse("2a is not straight forward to 1a", pos.isStraightForward(position(1, 'a')))
    }

    @Test
    fun testIsStraightBackwardValid() {
        val pos = position(2, 'a')
        assertTrue("1a is straight backward to 2a", pos.isStraightBackward(position(1, 'a')))
    }

    @Test
    fun testIsNotStraightBackward() {
        val pos = position(1, 'a')
        assertFalse("2a is not straight backward to 1a", pos.isStraightBackward(position(2, 'a')))
    }

    @Test
    fun testIsForwardDiagonalRight() {
        val pos = position(1, 'a')
        assertTrue("1a is forward right to 2b", pos.isForwardDiagonal(position(2, 'b')))
    }

    @Test
    fun testIsNotForwardDiagonalRightInvalidDiagonal() {
        val pos = position(1, 'a')
        assertFalse("1b is not forward right to 4h", pos.isForwardDiagonal(position(4, 'h')))
    }

    @Test
    fun testIsNotForwardDiagonalRightBackwardPosition() {
        val pos = position(2, 'b')
        assertFalse("2b is not forward right to 1a", pos.isForwardDiagonal(position(1, 'a')))
    }

    @Test
    fun testIsForwardDiagonalLeft() {
        val pos = position(1, 'b')
        assertTrue("1b is forward left to 2a", pos.isForwardDiagonal(position(2, 'a')))
    }

    @Test
    fun testIsNotForwardDiagonalLeftInvalidDiagonal() {
        val pos = position(2, 'b')
        assertFalse("2b is not forward left to 4a", pos.isForwardDiagonal(position(4, 'a')))
    }

    @Test
    fun testIsNotForwardDiagonalLeftBackwardPosition() {
        val pos = position(2, 'b')
        assertFalse("2b is not forward left to 1c", pos.isForwardDiagonal(position(1, 'c')))
    }

    @Test
    fun testIsBackwardDiagonalRight() {
        val pos = position(2, 'b')
        assertTrue("2b is backward right to 1c", pos.isBackwardDiagonal(position(1, 'c')))
    }

    @Test
    fun testIsNotBackwardDiagonalRightForwardPosition() {
        val pos = position(2, 'b')
        assertFalse("2b is not backward right to 3c", pos.isBackwardDiagonal(position(3, 'c')))
    }

    @Test
    fun testIsBackwardDiagonalLeft() {
        val pos = position(2, 'b')
        assertTrue("2b is backward left to 1a", pos.isBackwardDiagonal(position(1, 'a')))
    }

    @Test
    fun testIsNotBackwardDiagonalLeftForwardPosition() {
        val pos = position(2, 'b')
        assertFalse("2b is not forward left to 3a", pos.isBackwardDiagonal(position(3, 'a')))
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