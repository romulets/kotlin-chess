package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position

class Queen(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun moveTo(position: Position) : Boolean {
        TODO("not implemented")
    }

    override fun possiblePositions(positionIsFull: (position: Position) -> Boolean): List<Position> {
        TODO("not implemented")
    }
}