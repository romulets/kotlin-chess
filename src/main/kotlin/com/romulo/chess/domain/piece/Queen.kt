package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position

class Queen(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun moveTo(position: Position, pieceAt: (position: Position) -> Piece?): Boolean {
        TODO("not implemented")
    }

    override fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position> {
        val possiblePositions = ArrayList<Position>()
        val rook = Rook(color, position)
        val bishop = Bishop(color, position)

        possiblePositions.addAll(rook.possiblePositions(pieceAt))
        possiblePositions.addAll(bishop.possiblePositions(pieceAt))

        return possiblePositions
    }
}