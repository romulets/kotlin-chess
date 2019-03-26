package com.romulo.chess.domain

import com.romulo.chess.domain.Color.BLACK
import com.romulo.chess.domain.Color.WHITE
import com.romulo.chess.domain.piece.*

class GameBoard {

    var player: Color = WHITE
    val pieces: MutableList<Piece> = ArrayList()

    init {
        pieces.add(Rook(WHITE, fullPosition(1, 'a')))
        pieces.add(Knight(WHITE, fullPosition(1, 'b')))
        pieces.add(Bishop(WHITE, fullPosition(1, 'c')))
        pieces.add(Queen(WHITE, fullPosition(1, 'd')))
        pieces.add(King(WHITE, fullPosition(1, 'e')))
        pieces.add(Bishop(WHITE, fullPosition(1, 'f')))
        pieces.add(Knight(WHITE, fullPosition(1, 'g')))
        pieces.add(Rook(WHITE, fullPosition(1, 'h')))
        setUpPawns(2, WHITE)

        setUpPawns(7, BLACK)
        pieces.add(Rook(BLACK, fullPosition(8, 'a')))
        pieces.add(Knight(BLACK, fullPosition(8, 'b')))
        pieces.add(Bishop(BLACK, fullPosition(8, 'c')))
        pieces.add(King(BLACK, fullPosition(8, 'd')))
        pieces.add(Queen(BLACK, fullPosition(8, 'e')))
        pieces.add(Bishop(BLACK, fullPosition(8, 'f')))
        pieces.add(Knight(BLACK, fullPosition(8, 'g')))
        pieces.add(Rook(BLACK, fullPosition(8, 'h')))
    }

    private fun setUpPawns(number: Int, color: Color) {
        for (letter in 97..104) {
            pieces.add(
                Pawn(
                    color,
                    Position(number, letter.toChar())
                )
            )
        }
    }

    fun pieceAt(position: Position): Piece? {
        return pieces.firstOrNull { p -> p.position.sameThat(position) }
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

    private fun fillPosition(position: Position): Position {
        if (pieceAt(position) != null) {
            return position.fullPosition()
        }

        return position.emptyPosition()
    }


}
