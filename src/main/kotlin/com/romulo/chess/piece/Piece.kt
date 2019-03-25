package com.romulo.chess.piece

import com.romulo.chess.Position

interface Piece {
    val color: Color
    val position: Position

    fun canWalk(possiblePosition: Position) : Boolean
    fun walk(position: Position)

}