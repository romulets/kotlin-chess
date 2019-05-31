package com.romulo.chess.domain

import com.romulo.chess.domain.Color.BLACK
import com.romulo.chess.domain.Color.WHITE
import com.romulo.chess.domain.piece.Bishop
import com.romulo.chess.domain.piece.King
import com.romulo.chess.domain.piece.Knight
import com.romulo.chess.domain.piece.Pawn
import com.romulo.chess.domain.piece.Piece
import com.romulo.chess.domain.piece.Queen
import com.romulo.chess.domain.piece.Rook
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.reflect.KClass

class GameBoardTest {

    @Test
    fun testInitialState() {
        val gameBoard = GameBoard()

        assertEquals(gameBoard.status, GameStatus.OnGoing)

        assertEquals(32, gameBoard.pieces.size)
        assertEquals(0, gameBoard.eatenPieces.size)

        assertPieceIs(gameBoard, Position(1, 'a'), Rook::class, WHITE)
        assertPieceIs(gameBoard, Position(1, 'b'), Knight::class, WHITE)
        assertPieceIs(gameBoard, Position(1, 'c'), Bishop::class, WHITE)
        assertPieceIs(gameBoard, Position(1, 'd'), Queen::class, WHITE)
        assertPieceIs(gameBoard, Position(1, 'e'), King::class, WHITE)
        assertPieceIs(gameBoard, Position(1, 'f'), Bishop::class, WHITE)
        assertPieceIs(gameBoard, Position(1, 'g'), Knight::class, WHITE)
        assertPieceIs(gameBoard, Position(1, 'h'), Rook::class, WHITE)
        assertRowIsAllPawns(gameBoard, 2, WHITE)

        assertRowIsAllNull(gameBoard, 3)
        assertRowIsAllNull(gameBoard, 4)
        assertRowIsAllNull(gameBoard, 5)
        assertRowIsAllNull(gameBoard, 6)

        assertRowIsAllPawns(gameBoard, 7, BLACK)
        assertPieceIs(gameBoard, Position(8, 'a'), Rook::class, BLACK)
        assertPieceIs(gameBoard, Position(8, 'b'), Knight::class, BLACK)
        assertPieceIs(gameBoard, Position(8, 'c'), Bishop::class, BLACK)
        assertPieceIs(gameBoard, Position(8, 'd'), Queen::class, BLACK)
        assertPieceIs(gameBoard, Position(8, 'e'), King::class, BLACK)
        assertPieceIs(gameBoard, Position(8, 'f'), Bishop::class, BLACK)
        assertPieceIs(gameBoard, Position(8, 'g'), Knight::class, BLACK)
        assertPieceIs(gameBoard, Position(8, 'h'), Rook::class, BLACK)
    }

    @Test
    fun testPlay() {
        val gameBoard = GameBoard()

        gameBoard.play(Position(2, 'a'), Position(3, 'a'))

        assertEquals(gameBoard.status, GameStatus.OnGoing)
        assertPieceIs(
            gameBoard,
            Position(3, 'a'), Pawn::class, WHITE
        )
    }

    @Test
    fun testPlayChangePlayers() {
        val gameBoard = GameBoard()

        assertEquals(WHITE, gameBoard.player)

        gameBoard.play(Position(2, 'a'), Position(3, 'a'))

        assertPieceIs(
            gameBoard,
            Position(3, 'a'), Pawn::class, WHITE
        )

        assertEquals(BLACK, gameBoard.player)
    }

    @Test
    fun testPlayEmptySquare() {
        assertThrows(IllegalArgumentException::class.java, {
            val gameBoard = GameBoard()
            gameBoard.play(Position(3, 'a'), Position(3, 'a'))
        }, "Play in empty square")
    }

    @Test
    fun testInvalidPlay() {
        assertThrows(IllegalArgumentException::class.java, {
            val gameBoard = GameBoard()
            gameBoard.play(Position(2, 'a'), Position(5, 'b'))
        }, "InvalidPlay")
    }

    @Test
    fun testPossiblePlaysForEmptyPosition() {
        assertThrows(IllegalArgumentException::class.java, {
            val gameBoard = GameBoard()
            gameBoard.possiblePlays(Position(3, 'b'))
        }, "InvalidPlay")
    }

    @Test
    fun testPossiblePlaysForValidPosition() {
        val gameBoard = GameBoard()
        val possiblePlays = gameBoard.possiblePlays(Position(2, 'b'))
        assertEquals(2, possiblePlays.size)
    }

    @Test
    fun testEatScenario() {
        val board = GameBoard()
        board.play(Position(2, 'e'), Position(4, 'e'))
        board.play(Position(7, 'f'), Position(5, 'f'))
        board.play(Position(4, 'e'), Position(5, 'f'))

        assertEquals(31, board.pieces.size)
        assertEquals(1, board.eatenPieces.size)

        assertPieceIs(board, Position(5, 'f'), Pawn::class, WHITE)
    }

    @Test
    fun testCheckScenario() {
        val board = GameBoard()

        board.play(Position(1, 'b'), Position(3, 'c'))
        board.play(Position(7, 'c'), Position(5, 'c'))
        board.play(Position(3, 'c'), Position(5, 'b'))
        board.play(Position(7, 'd'), Position(5, 'd'))
        board.play(Position(5, 'b'), Position(7, 'c'))

        assertEquals(board.status, GameStatus.Check)
    }


    private fun assertRowIsAllPawns(gameBoard: GameBoard, row: Int, color: Color) {
        for (i in 97..104) {
            assertPieceIs(
                gameBoard,
                Position(row, i.toChar()), Pawn::class, color
            )
        }
    }

    private fun assertRowIsAllNull(gameBoard: GameBoard, row: Int) {
        for (i in 97..104) {
            assertNull(gameBoard.pieceAt(Position(row, i.toChar())))
        }
    }

    private fun assertPieceIs(gameBoard: GameBoard, Position: Position, type: KClass<out Piece>, color: Color) {
        val piece = gameBoard.pieceAt(Position)
        assertTrue(
            type.isInstance(piece),
            "Piece should be " + type.simpleName + " and not " + piece?.javaClass?.kotlin
        )
        assertEquals(color, piece?.color)
    }

}