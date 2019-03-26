package com.romulo.chess

import com.romulo.chess.piece.*
import com.romulo.chess.piece.Color.*

class GameBoard {

    private val gameBoard : MutableList<Piece> = ArrayList()

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

    fun pieceAt(position: Position): Piece? {
        return gameBoard.filter { p -> p.position.sameThat(position) }.firstOrNull()
    }

}
