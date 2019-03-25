package com.romulo.chess

import com.romulo.chess.piece.*
import com.romulo.chess.piece.Color.*

class GameBoard {

    private val gameBoard : MutableList<Piece> = ArrayList()

    init {
        gameBoard.add(Rook(WHITE, Position(1, 'a')))
        gameBoard.add(Knight(WHITE, Position(1, 'b')))
        gameBoard.add(Bishop(WHITE, Position(1, 'c')))
        gameBoard.add(Queen(WHITE, Position(1, 'd')))
        gameBoard.add(King(WHITE, Position(1, 'e')))
        gameBoard.add(Bishop(WHITE, Position(1, 'f')))
        gameBoard.add(Knight(WHITE, Position(1, 'g')))
        gameBoard.add(Rook(WHITE, Position(1, 'h')))
        setUpPawns(2, WHITE)

        setUpPawns(7, BLACK)
        gameBoard.add(Rook(BLACK, Position(8, 'a')))
        gameBoard.add(Knight(BLACK, Position(8, 'b')))
        gameBoard.add(Bishop(BLACK, Position(8, 'c')))
        gameBoard.add(King(BLACK, Position(8, 'd')))
        gameBoard.add(Queen(BLACK, Position(8, 'e')))
        gameBoard.add(Bishop(BLACK, Position(8, 'f')))
        gameBoard.add(Knight(BLACK, Position(8, 'g')))
        gameBoard.add(Rook(BLACK, Position(8, 'h')))
    }

    private fun setUpPawns(number: Int, color: Color) {
        for (letter in 97..104) {
            gameBoard.add(Pawn(color, Position(number, letter.toChar())))
        }
    }

    fun pieceAt(number: Int, letter: Char): Piece? {
        return gameBoard.filter { p -> p.position.isAt(number, letter) }.firstOrNull()
    }

}
