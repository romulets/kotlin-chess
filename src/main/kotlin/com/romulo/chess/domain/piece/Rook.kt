package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.MAX_POSITION_NUMBER
import com.romulo.chess.domain.MIN_POSITION_NUMBER
import com.romulo.chess.domain.Position

class Rook(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun moveTo(position: Position, pieceAt: (position: Position) -> Piece?): Boolean {
        TODO("not implemented")
    }

    override fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position> {
        TODO("not implemented")
    }

}