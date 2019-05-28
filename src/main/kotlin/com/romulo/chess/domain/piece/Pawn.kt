package com.romulo.chess.domain.piece

import com.romulo.chess.domain.*

class Pawn(
    override val color: Color,
    override var position: Position
) : Piece {

    private var walks = 0

    private fun canMoveTo(position: Position): Boolean {
        val possiblePositions = possiblePositions { possiblePosition: Position ->
            if (position.sameThat(possiblePosition)) position.full else false
        }
        return possiblePositions.contains(position)
    }

    override fun moveTo(position: Position): Boolean {
        if (!canMoveTo(position)) {
            return false
        }

        this.position = position
        walks++

        return true
    }

    override fun possiblePositions(positionIsFull: (position: Position) -> Boolean): List<Position> {
        val possiblePositions = ArrayList<Position>()

        nextPosition(1, 0)?.let { position ->
            if (!positionIsFull(position)) possiblePositions.add(position.emptyPosition())
        }

        nextPosition(2, 0)?.let { position ->
            if (walks == 0 && !positionIsFull(position)) possiblePositions.add(position.emptyPosition())
        }

        nextPosition(1, 1)?.let { position ->
            if (positionIsFull(position)) possiblePositions.add(position.fullPosition())
        }

        nextPosition(1, -1)?.let { position ->
            if (positionIsFull(position)) possiblePositions.add(position.fullPosition())
        }

        return possiblePositions
    }

    private fun nextPosition(numbers: Int, letters: Int): Position? {
        val nextNumber: Int
        val nextLetter: Char

        if (color == Color.WHITE) {
            nextNumber = position.number + numbers
            nextLetter = position.letter.plus(letters)
        } else {
            nextNumber = position.number - numbers
            nextLetter = position.letter.minus(letters)
        }

        return if (validPosition(nextNumber, nextLetter)) {
            position(nextNumber, nextLetter)
        } else {
            null
        }
    }

}
