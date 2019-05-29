package com.romulo.chess.domain

import com.romulo.chess.domain.Color.BLACK
import com.romulo.chess.domain.Color.WHITE
import com.romulo.chess.domain.piece.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
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
        assertPieceIs(gameBoard, position(8, 'd'), Queen::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'e'), King::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'f'), Bishop::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'g'), Knight::class, BLACK)
        assertPieceIs(gameBoard, position(8, 'h'), Rook::class, BLACK)
    }

    @Test
    fun testPlayChangePlayers() {
        val gameBoard = GameBoard()

        assertEquals(WHITE, gameBoard.player)

        gameBoard.play(position(2, 'a'), position(3, 'a'))

        assertPieceIs(
            gameBoard,
            position(3, 'a'), Pawn::class, WHITE
        )
        assertEquals(BLACK, gameBoard.player)
    }

    @Test
    fun testPlayEmptySquare() {
        assertThrows(IllegalArgumentException::class.java, {
            val gameBoard = GameBoard()
            gameBoard.play(position(3, 'a'), position(3, 'a'))
        }, "Play in empty square")
    }

    @Test
    fun testInvalidPlay() {
        assertThrows(IllegalArgumentException::class.java, {
            val gameBoard = GameBoard()
            gameBoard.play(position(2, 'a'), position(5, 'b'))
        }, "InvalidPlay")
    }

    @Test
    fun testPossiblePlaysForEmptyPosition() {
        assertThrows(IllegalArgumentException::class.java, {
            val gameBoard = GameBoard()
            gameBoard.possiblePlays(position(3, 'b'))
        }, "InvalidPlay")
    }

    @Test
    fun testPossiblePlaysForValidPosition() {
        val gameBoard = GameBoard()
        val possiblePlays = gameBoard.possiblePlays(position(2, 'b'))
        assertEquals(2, possiblePlays.size)
    }

    @Test
    fun testEatScenario() {
        val board = GameBoard()
        board.play(position(2, 'e'), position(4, 'e'))
        board.play(position(7, 'f'), position(5, 'f'))
        board.play(position(4, 'e'), position(5, 'f'))

        assertEquals(31, board.pieces.size)
        assertEquals(1, board.eatenPieces.size)

        assertPieceIs(board, position(5, 'f'), Pawn::class, WHITE)
    }

    private fun assertRowIsAllPawns(gameBoard: GameBoard, row: Int, color: Color) {
        for (i in 97..104) {
            assertPieceIs(
                gameBoard,
                fullPosition(row, i.toChar()), Pawn::class, color
            )
        }
    }

    private fun assertRowIsAllNull(gameBoard: GameBoard, row: Int) {
        for (i in 97..104) {
            assertNull(gameBoard.pieceAt(position(row, i.toChar())))
        }
    }

    private fun assertPieceIs(gameBoard: GameBoard, position: Position, type: KClass<out Piece>, color: Color) {
        val piece = gameBoard.pieceAt(position)
        assertTrue(
            type.isInstance(piece),
            "Piece should be " + type.simpleName + " and not " + piece?.javaClass?.kotlin
        )
        assertEquals(color, piece?.color)
    }

}