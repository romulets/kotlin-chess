package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import com.romulo.chess.domain.fullPosition
import com.romulo.chess.domain.position

class Pawn(
    override val color: Color,
    override var position: Position
) : Piece {

    private var walks = 0

    private fun canMoveTo(possiblePosition: Position): Boolean {
        return canWhiteWalk(possiblePosition) ||
                canBlackWalk(possiblePosition) ||
                canWhiteEat(possiblePosition) ||
                canBlackEat(possiblePosition)
    }

    private fun canWhiteWalk(possiblePosition: Position) : Boolean {
        return color == Color.WHITE && position.isStraightForward(possiblePosition) &&
                (isOneSquareWalk(possiblePosition) || isFirstTimeTwoSquaresWalk(possiblePosition))
    }

    private fun canBlackWalk(possiblePosition: Position) : Boolean {
        return color == Color.BLACK && position.isStraightBackward(possiblePosition) &&
                (isOneSquareWalk(possiblePosition) || isFirstTimeTwoSquaresWalk(possiblePosition))
    }

    private fun canWhiteEat(possiblePosition: Position) : Boolean {
        return color == Color.WHITE && position.isForwardDiagonal(possiblePosition) &&
                isOneSquareWalk(possiblePosition) && possiblePosition.full
    }

    private fun canBlackEat(possiblePosition: Position) : Boolean {
        return color == Color.BLACK && position.isBackwardDiagonal(possiblePosition) &&
                isOneSquareWalk(possiblePosition) && possiblePosition.full
    }

    private fun isOneSquareWalk(possiblePosition: Position): Boolean = position.distanceTo(possiblePosition) == 1

    private fun isFirstTimeTwoSquaresWalk(possiblePosition: Position) : Boolean  {
        return position.distanceTo(possiblePosition) == 2 && walks == 0
    }

    override fun moveTo(position: Position) : Boolean {
        if (!canMoveTo(position)) {
            return false
        }

        this.position = position
        walks++

        return true
    }

    override fun possiblePositions(pieceAt : (position : Position) -> Piece?): List<Position> {
        val possiblePositions = ArrayList<Position>()

        val nextPosition = nextPosition(1, 0).emptyPosition()
        if (pieceAt(nextPosition) == null) {
            possiblePositions.add(nextPosition)
        }

        val jumpPosition = nextPosition(2, 0).emptyPosition()
        if (walks == 0 && pieceAt(jumpPosition) == null) {
            possiblePositions.add(jumpPosition)
        }

        val eatRight = nextPosition(1, 1).fullPosition()
        if (pieceAt(eatRight) !== null) {
            possiblePositions.add(eatRight)
        }

        val eatLeft = nextPosition(1, -1).fullPosition()
        if (pieceAt(eatLeft) !== null) {
            possiblePositions.add(eatLeft)
        }

        return possiblePositions
    }

    private fun nextPosition(numbers : Int, letters: Int) : Position {
        return if (color == Color.WHITE) {
            position(position.number + numbers, position.letter + letters)
        } else {
            position(position.number - numbers, position.letter - letters)
        }
    }

}
