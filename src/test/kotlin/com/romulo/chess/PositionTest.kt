package com.romulo.chess

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class PositionTest {

    @Test
    fun testValidPosition() {
        val pos = Position(1, 'a')
        Assert.assertEquals(1, pos.number)
        Assert.assertEquals('a', pos.letter)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidRow() {
        Position(0, 'g')
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidLetter() {
        Position(0, 'i')
    }

    @Test
    fun testIsStraightForwardValid() {
        val pos = Position(1, 'a');
        assertTrue("1a is straight forward to 2a", pos.isStraightForward(Position(2, 'a')))
    }

    @Test
    fun testIsStraightForwardInvalid() {
        val pos = Position(2, 'a');
        assertFalse("2a is not straight forward to 1a", pos.isStraightForward(Position(1, 'a')))
    }

    @Test
    fun testIsStraightBackwardValid() {
        val pos = Position(2, 'a');
        assertTrue("1a is straight backward to 2a", pos.isStraightBackward(Position(1, 'a')))
    }

    @Test
    fun testIsStraightBackwardInvalid() {
        val pos = Position(1, 'a');
        assertFalse("2a is not straight backward to 1a", pos.isStraightBackward(Position(2, 'a')))
    }

    @Test
    fun testDistanceToStraightForward() {
        val pos1 = Position(1, 'a');
        val pos2 = Position(8, 'a');
        assertEquals(7, pos1.distanceTo(pos2))
    }

    @Test
    fun testDistanceToBackward() {
        val pos1 = Position(8, 'a');
        val pos2 = Position(1, 'a');
        assertEquals(7, pos1.distanceTo(pos2))
    }

}