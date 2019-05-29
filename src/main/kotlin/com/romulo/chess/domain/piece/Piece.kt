package com.romulo.chess.domain.piece

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import com.romulo.chess.domain.nullablePosition

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "type")
interface Piece {
    val color: Color
    val position: Position

    fun moveTo(position: Position, pieceAt: (position: Position) -> Piece? = { null }): Boolean
    fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position>

    fun isOpponentPiece(other: Piece?): Boolean {
        if (other == null) {
            return false
        }

        return other.color != color
    }

    fun possibleSequentialPositions(
        range: Iterable<Pair<Int, Char>>,
        pieceAt: (position: Position) -> Piece?
    ): List<Position> {
        val possiblePositions = ArrayList<Position>()

        for ((number, letter) in range) {
            val possiblePosition = nullablePosition(number, letter) ?: break
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