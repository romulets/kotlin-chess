package com.romulo.chess.piece

import com.romulo.chess.Position

class Knight(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun moveTo(position: Position) : Boolean {
        TODO("not implemented")
    }

}