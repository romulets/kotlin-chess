package com.romulo.chess.domain.piece

import com.romulo.chess.domain.*

class Pawn(
    override val color: Color,
    override var position: Position
) : Piece {

    private var walks = 0

    override fun moveTo(position: Position, pieceAt: (position: Position) -> Piece?): Boolean {
        if (!possiblePositions(pieceAt).contains(position)) {
            return false
        }

        this.position = position
        walks++

        return true
    }

    override fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position> {
        val possiblePositions = ArrayList<Position>()

        nextPosition(1, 0)?.let { position ->
            if (pieceAt(position) == null) possiblePositions.add(position)
        }

        nextPosition(2, 0)?.let { position ->
            if (walks == 0 && pieceAt(position) == null) possiblePositions.add(position)
        }

        nextPosition(1, 1)?.let { position ->
            val piece = pieceAt(position)
            if (piece != null && isOpponentPiece(piece)) {
                possiblePositions.add(position)
            }
        }

        nextPosition(1, -1)?.let { position ->
            val piece = pieceAt(position)
            if (piece != null && isOpponentPiece(piece)) {
                possiblePositions.add(position)
            }
        }

        return possiblePositions
    }

    private fun nextPosition(numbers: Int, letters: Int): Position? {
        return if (color == Color.WHITE) {
            position.add(numbers, letters)
        } else {
            position.add(-numbers, -letters)
        }
    }

}
