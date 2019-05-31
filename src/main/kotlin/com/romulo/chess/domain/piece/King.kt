package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position

class King(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position> {
        return getNotFriendPsositions(
            this,
            listOf(
                position.add(1, 1),
                position.add(1, 0),
                position.add(1, -1),
                position.add(0, 1),
                position.add(0, -1),
                position.add(-1, 1),
                position.add(-1, 0),
                position.add(-1, -1)
            ),
            pieceAt
        )
    }

    override fun moveTo(position: Position, pieceAt: (position: Position) -> Piece?): Boolean {
        TODO("not implemented")
    }
}