package com.romulo.chess.piece

import com.romulo.chess.Position

class King(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun canWalk(possiblePosition: Position): Boolean {
        TODO("not implemented")
    }

    override fun walk(position: Position) {
        TODO("not implemented")
    }

}