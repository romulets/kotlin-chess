package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position

class King(
    override val color: Color,
    override val position: Position
) : Piece {
    override fun possiblePositions(pieceAt : (position : Position) -> Piece?): List<Position> {
        TODO("not implemented")
    }

    override fun moveTo(position: Position) : Boolean {
        TODO("not implemented")
    }
}