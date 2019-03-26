package com.romulo.chess.piece

import com.romulo.chess.Position

class Pawn(
    override val color: Color,
    override var position: Position
) : Piece {

    private var walks = 0

    override fun canMove(possiblePosition: Position): Boolean {
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

    override fun move(position: Position) {
        this.position = position
        walks++
    }

}
