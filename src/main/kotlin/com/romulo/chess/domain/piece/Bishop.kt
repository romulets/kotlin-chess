package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.MAX_POSITION_LETTER
import com.romulo.chess.domain.MAX_POSITION_NUMBER
import com.romulo.chess.domain.MIN_POSITION_LETTER
import com.romulo.chess.domain.MIN_POSITION_NUMBER
import com.romulo.chess.domain.Position
import com.romulo.chess.domain.nullablePosition


class Bishop(
    override val color: Color,
    override val position: Position
) : Piece {

    override fun moveTo(position: Position, pieceAt: (position: Position) -> Piece?): Boolean {
        TODO("not implemented")
    }

    override fun possiblePositions(pieceAt: (position: Position) -> Piece?): List<Position> {
        val possiblePositions = ArrayList<Position>()

        possiblePositions.addAll(
            possibleSequentialPositions(
                position.number.dec().downTo(MIN_POSITION_NUMBER)
                    .zip(position.letter.inc().rangeTo(MAX_POSITION_LETTER)),
                pieceAt
            )
        )

        possiblePositions.addAll(
            possibleSequentialPositions(
                position.number.inc().rangeTo(MAX_POSITION_NUMBER)
                    .zip(position.letter.dec().downTo(MIN_POSITION_LETTER)),
                pieceAt
            )
        )

        possiblePositions.addAll(
            possibleSequentialPositions(
                position.number.inc().rangeTo(MAX_POSITION_NUMBER)
                    .zip(position.letter.inc().rangeTo(MAX_POSITION_LETTER)),
                pieceAt
            )
        )

        possiblePositions.addAll(
            possibleSequentialPositions(
                position.number.dec().downTo(MIN_POSITION_NUMBER)
                    .zip(position.letter.dec().downTo(MIN_POSITION_LETTER)),
                pieceAt
            )
        )

        return possiblePositions
    }
}
