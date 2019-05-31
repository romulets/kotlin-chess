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
    fromPiece: Piece,
    range: Iterable<Pair<Int, Char>>,
    pieceAt: (position: Position) -> Piece?
): List<Position> {

    val possiblePositions = ArrayList<Position>()
    for ((number, letter) in range) {
        val toPosition = nullablePosition(number, letter) ?: break

        getNotFriendPosition(fromPiece, toPosition, pieceAt)?.let(possiblePositions::add)

        if (pieceAt(toPosition) !== null) {
            break
        }
    }

    return possiblePositions
}

fun getNotFriendPosition(fromPiece: Piece, position: Position?, pieceAt: (position: Position) -> Piece?): Position? {
    position?.let {
        val possiblePieceToEat = pieceAt(position)
        if (possiblePieceToEat == null || opponentPieces(fromPiece, possiblePieceToEat)) {
            return position
        }
    }

    return null
}

fun getNotFriendPsositions(
    fromPiece: Piece,
    positions: List<Position?>,
    pieceAt: (position: Position) -> Piece?
): List<Position> {
    val notFriendPositions = ArrayList<Position>()

    for (position in positions) {
        getNotFriendPosition(fromPiece, position, pieceAt)?.let(notFriendPositions::add)
    }

    return notFriendPositions
}