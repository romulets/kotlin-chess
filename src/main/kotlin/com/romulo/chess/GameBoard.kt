package com.romulo.chess

import com.romulo.chess.Color.BLACK
import com.romulo.chess.Color.WHITE
import com.romulo.chess.piece.*

class GameBoard {

    private var player: Color = WHITE
    private val gameBoard: MutableList<Piece> = ArrayList()

    init {
        gameBoard.add(Rook(WHITE, fullPosition(1, 'a')))
        gameBoard.add(Knight(WHITE, fullPosition(1, 'b')))
        gameBoard.add(Bishop(WHITE, fullPosition(1, 'c')))
        gameBoard.add(Queen(WHITE, fullPosition(1, 'd')))
        gameBoard.add(King(WHITE, fullPosition(1, 'e')))
        gameBoard.add(Bishop(WHITE, fullPosition(1, 'f')))
        gameBoard.add(Knight(WHITE, fullPosition(1, 'g')))
        gameBoard.add(Rook(WHITE, fullPosition(1, 'h')))
        setUpPawns(2, WHITE)

        setUpPawns(7, BLACK)
        gameBoard.add(Rook(BLACK, fullPosition(8, 'a')))
        gameBoard.add(Knight(BLACK, fullPosition(8, 'b')))
        gameBoard.add(Bishop(BLACK, fullPosition(8, 'c')))
        gameBoard.add(King(BLACK, fullPosition(8, 'd')))
        gameBoard.add(Queen(BLACK, fullPosition(8, 'e')))
        gameBoard.add(Bishop(BLACK, fullPosition(8, 'f')))
        gameBoard.add(Knight(BLACK, fullPosition(8, 'g')))
        gameBoard.add(Rook(BLACK, fullPosition(8, 'h')))
    }

    private fun setUpPawns(number: Int, color: Color) {
        for (letter in 97..104) {
            gameBoard.add(Pawn(color, Position(number, letter.toChar())))
        }
    }

    fun player(): Color = this.player

    fun pieceAt(position: Position): Piece? {
        return gameBoard.filter { p -> p.position.sameThat(position) }.firstOrNull()
    }

    fun movePieceFromTo(from: Position, to: Position) {
        val piece = pieceAt(from) ?: throw IllegalArgumentException("Empty square")

        val couldMove = piece.moveTo(fillPosition(to))

        if (!couldMove) {
            throw IllegalArgumentException("Invalid play")
        }

        changePlayers()
    }

    private fun changePlayers() {
        player = if (player == WHITE) {
            BLACK
        } else {
            WHITE
        }
    }

    fun fillPosition(position: Position): Position {
        if (pieceAt(position) != null) {
            return position.fullPosition()
        }

        return position.emptyPosition()
    }


}
