package com.romulo.chess.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PositionTest {

    @Test
    fun testValidPosition() {
        val pos = Position(1, 'a')
        assertEquals(1, pos.number)
        assertEquals('a', pos.letter)
    }

    @Test
    fun testInvalidRow() {
        assertThrows(IllegalArgumentException::class.java) {
            Position(0, 'g')
        }
    }

    @Test
    fun testInvalidLetter() {
        assertThrows(IllegalArgumentException::class.java) {
            Position(0, 'i')
        }
    }

    @Test
    fun testAdd() {
        val pos = Position(1, 'a')
        assertEquals(Position(3, 'd'), pos.add(2, 3))
    }

    @Test
    fun testSubtract() {
        val pos = Position(8, 'h')
        assertEquals(Position(6, 'e'), pos.add(-2, -3))
    }

    @Test
    fun testAddInvalidPos() {
        val pos = Position(8, 'h')
        assertEquals(null, pos.add(2, 3))
    }

}