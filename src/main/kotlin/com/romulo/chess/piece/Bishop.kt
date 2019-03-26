package com.romulo.chess.piece

import com.romulo.chess.Position


class Bishop(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun canMove(possiblePosition: Position): Boolean {
        TODO("not implemented")
    }

    override fun move(position: Position) {
        TODO("not implemented")
    }
}
