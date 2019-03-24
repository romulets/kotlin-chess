package com.romulo.chess

import com.romulo.chess.piece.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.reflect.KClass

class GameBoardTest {

    @Test
    fun testInitialState() {
        val gameBoard = GameBoard()

        assertPieceIs(gameBoard.pieceAt(1, 'a'), Rook::class, Color.WHITE)
        assertPieceIs(gameBoard.pieceAt(1, 'b'), Knight::class, Color.WHITE)
        assertPieceIs(gameBoard.pieceAt(1, 'c'), Bishop::class, Color.WHITE)
        assertPieceIs(gameBoard.pieceAt(1, 'd'), Queen::class, Color.WHITE)
        assertPieceIs(gameBoard.pieceAt(1, 'e'), King::class, Color.WHITE)
        assertPieceIs(gameBoard.pieceAt(1, 'f'), Bishop::class, Color.WHITE)
        assertPieceIs(gameBoard.pieceAt(1, 'g'), Knight::class, Color.WHITE)
        assertPieceIs(gameBoard.pieceAt(1, 'h'), Rook::class, Color.WHITE)
    }

    private fun assertPieceIs(piece: Piece?, type: KClass<out Piece>, color: Color) {
        assertTrue("Piece is " + type.simpleName, type.isInstance(piece))
        assertEquals(color, piece?.color)
    }

}