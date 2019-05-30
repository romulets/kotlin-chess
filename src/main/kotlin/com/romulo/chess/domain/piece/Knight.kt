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
        val possiblePositions = ArrayList<Position>()
        getNotFriendPosition(this, position.add(2, 1), pieceAt)?.let(possiblePositions::add)
        getNotFriendPosition(this, position.add(2, -1), pieceAt)?.let(possiblePositions::add)
        getNotFriendPosition(this, position.add(-2, 1), pieceAt)?.let(possiblePositions::add)
        getNotFriendPosition(this, position.add(-2, -1), pieceAt)?.let(possiblePositions::add)
        getNotFriendPosition(this, position.add(1, 2), pieceAt)?.let(possiblePositions::add)
        getNotFriendPosition(this, position.add(1, -2), pieceAt)?.let(possiblePositions::add)
        getNotFriendPosition(this, position.add(-1, 2), pieceAt)?.let(possiblePositions::add)
        getNotFriendPosition(this, position.add(-1, -2), pieceAt)?.let(possiblePositions::add)
        return possiblePositions
    }
}