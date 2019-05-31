package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position

class Knight(
    override val color: Color,
    override var position: Position
) : Piece {

    override fun moveTo(position: Position, pieceAt: (position: Position) -> Piece?): Boolean {
        if (!possiblePositions(pieceAt).contains(position)) {
            return false
        }

        this.position = position

        return true
    }

    override fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position> {
        return getNotFriendPsositions(
            this,
            listOf(
                position.add(2, 1),
                position.add(2, -1),
                position.add(-2, 1),
                position.add(-2, -1),
                position.add(1, 2),
                position.add(1, -2),
                position.add(-1, 2),
                position.add(-1, -2)
            ),
            pieceAt
        )
    }
}