package com.romulo.chess

import com.romulo.chess.piece.*
import com.romulo.chess.Color.BLACK
import com.romulo.chess.Color.WHITE
import org.junit.Assert.*
import org.junit.Test
import kotlin.reflect.KClass

class GameBoardTest {

    @Test
    fun testInitialState() {
        val gameBoard = GameBoard()

        assertPieceIs(gameBoard, position(1, 'a'), Rook::class, WHITE)
        assertPieceIs(gameBoard, position(1, 'b'), Knight::class, WHITE)
        assertPieceIs(gameBoard, position(1, 'c'), Bishop::class, WHITE)
        assertPieceIs(gameBoard, position(1, 'd'), Queen::class, WHITE)
        assertPieceIs(gameBoard, position(1, 'e'), King::class, WHITE)
        assertPieceIs(gameBoard, position(1, 'f'), Bishop::class, WHITE)
        assertPieceIs(gameBoard, position(1, 'g'), Knight::class, WHITE)
        assertPieceIs(gameBoard, position(1, 'h'), Rook::class, WHITE)
        assertRowIsAllPawns(gameBoard, 2, WHITE)

        assertRowIsAllNull(gameBoard, 3)
        assertRowIsAllNull(gameBoard, 4)
        assertRowIsAllNull(gameBoard, 5)
        assertRowIsAllNull(gameBoard, 6)

        assertRowIsAllPawns(gameBoard, 7, BLACK)
        assertPieceIs(gameBoard, position(8, 'a'), Rook::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'b'), Knight::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'c'), Bishop::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'd'), King::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'e'), Queen::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'f'), Bishop::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'g'), Knight::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'h'), Rook::class, BLACK)

    }

    private fun assertRowIsAllPawns(gameBoard: GameBoard, row : Int, color: Color) {
        for(i in 97..104) {
            assertPieceIs(gameBoard, fullPosition(row, i.toChar()), Pawn::class, color)
        }
    }

    private fun assertRowIsAllNull(gameBoard: GameBoard, row : Int) {
        for(i in 97..104) {
            assertNull(gameBoard.pieceAt(position(row, i.toChar())))
        }
    }

    private fun assertPieceIs(gameBoard: GameBoard, position: Position, type: KClass<out Piece>, color: Color) {
        val piece = gameBoard.pieceAt(position)
        assertTrue("Piece should be " + type.simpleName + " and not " + piece?.javaClass?.kotlin, type.isInstance(piece))
        assertEquals(color, piece?.color)
    }

}