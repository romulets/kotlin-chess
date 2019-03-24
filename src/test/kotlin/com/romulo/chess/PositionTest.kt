package com.romulo.chess

import org.junit.Assert
import org.junit.Test

class PositionTest {

    @Test
    fun testValidPosition() {
        val pos = Position(1, 'a')
        Assert.assertEquals(1, pos.number)
        Assert.assertEquals('a', pos.letter)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testInvalidPosition() {
        Position(0, 'g')
    }

}