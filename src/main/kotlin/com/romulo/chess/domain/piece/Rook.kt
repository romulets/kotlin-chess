package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.MAX_POSITION_LETTER
import com.romulo.chess.domain.MAX_POSITION_NUMBER
import com.romulo.chess.domain.MIN_POSITION_LETTER
import com.romulo.chess.domain.MIN_POSITION_NUMBER
import com.romulo.chess.domain.Position
import com.romulo.chess.domain.nullablePosition

class Rook(
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

        possiblePositions.addAll(
            possibleVerticalPositions(position.number.dec().downTo(MIN_POSITION_NUMBER), pieceAt)
        )

        possiblePositions.addAll(
            possibleVerticalPositions(position.number.inc().rangeTo(MAX_POSITION_NUMBER), pieceAt)
        )

        possiblePositions.addAll(
            possibleHorizontalPositions(position.letter.toInt().dec().downTo(MIN_POSITION_LETTER), pieceAt)
        )

        possiblePositions.addAll(
            possibleHorizontalPositions(position.letter.toInt().inc().rangeTo(MAX_POSITION_LETTER), pieceAt)
        )

        return possiblePositions
    }

    private fun possibleVerticalPositions(
        range: IntProgression,
        pieceAt: (position: Position) -> Piece?
    ): List<Position> {
        val possiblePositions = ArrayList<Position>()
        for (number in range) {
            val possiblePosition = nullablePosition(number, position.letter) ?: break
            val piece = pieceAt(possiblePosition)
            if (piece === null || isOpponentPiece(piece)) {
                possiblePositions.add(possiblePosition)
            }
            if (piece !== null) {
                break
            }
        }
        return possiblePositions
    }

    private fun possibleHorizontalPositions(
        range: IntProgression,
        pieceAt: (position: Position) -> Piece?
    ): List<Position> {
        val possiblePositions = ArrayList<Position>()
        for (letter in range) {
            val possiblePosition = nullablePosition(position.number, letter.toChar()) ?: break
            val piece = pieceAt(possiblePosition)
            if (piece === null || isOpponentPiece(piece)) {
                possiblePositions.add(possiblePosition)
            }
            if (piece !== null) {
                break
            }
        }
        return possiblePositions
    }

}