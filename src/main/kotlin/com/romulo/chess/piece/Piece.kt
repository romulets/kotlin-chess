package com.romulo.chess.piece

import com.romulo.chess.Color
import com.romulo.chess.Position

interface Piece {
    val color: Color
    val position: Position

    fun moveTo(position: Position) : Boolean

}