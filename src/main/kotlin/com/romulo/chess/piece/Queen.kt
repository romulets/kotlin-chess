package com.romulo.chess.piece

import com.romulo.chess.Position

class Queen(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun canWalk(position: Position): Boolean {
        TODO("not implemented")
    }

    override fun move(position: Position) {
        TODO("not implemented")
    }

}