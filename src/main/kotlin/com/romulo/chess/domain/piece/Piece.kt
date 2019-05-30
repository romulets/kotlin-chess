package com.romulo.chess.domain.piece

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import com.romulo.chess.domain.nullablePosition

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
interface Piece {
    val color: Color
    val position: Position

    fun moveTo(position: Position, pieceAt: (position: Position) -> Piece? = { null }): Boolean
    fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position>

}

fun opponentPieces(pieceOne: Piece?, pieceTwo: Piece?): Boolean {
    if (pieceOne == null || pieceTwo == null) {
        return false
    }

    return pieceOne.color != pieceTwo.color
}


fun possibleSequentialPositions(
    inittalPiece: Piece,
    range: Iterable<Pair<Int, Char>>,
    pieceAt: (position: Position) -> Piece?
): List<Position> {
    val possiblePositions = ArrayList<Position>()

    for ((number, letter) in range) {
        val possiblePosition = nullablePosition(number, letter) ?: break
        val possiblePiece = pieceAt(possiblePosition)

        if (possiblePiece === null || opponentPieces(inittalPiece, possiblePiece)) {
            possiblePositions.add(possiblePosition)
        }

        if (possiblePiece !== null) {
            break
        }
    }

    return possiblePositions
}