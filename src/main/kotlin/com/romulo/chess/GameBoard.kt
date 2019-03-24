package com.romulo.chess

import com.romulo.chess.piece.*

class GameBoard {

    private val gameBoard : MutableMap<Position, Piece> = HashMap()

    init {
        gameBoard[Position(1, 'a')] = Rook(Color.WHITE)
        gameBoard[Position(1, 'b')] = Knight(Color.WHITE)
        gameBoard[Position(1, 'c')] = Bishop(Color.WHITE)
        gameBoard[Position(1, 'd')] = Queen(Color.WHITE)
        gameBoard[Position(1, 'e')] = King(Color.WHITE)
        gameBoard[Position(1, 'f')] = Bishop(Color.WHITE)
        gameBoard[Position(1, 'g')] = Knight(Color.WHITE)
        gameBoard[Position(1, 'h')] = Rook(Color.WHITE)
    }

    fun pieceAt(number: Int, letter: Char): Piece? {
        return gameBoard[Position(number, letter)]
    }

}
