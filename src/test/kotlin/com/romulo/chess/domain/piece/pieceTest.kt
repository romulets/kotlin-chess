package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position
import org.junit.jupiter.api.Assertions

fun returnPieceIf(condition: (position: Position) -> Boolean, color: Color): (position: Position) -> Piece? {
    return { p -> if (condition(p)) Pawn(color, p) else null }
}

fun assertPossiblePositionContains(possiblePositions: List<Position>, number: Int, letter: Char) {
    Assertions.assertTrue(
        possiblePositions.contains(Position(number, letter)),
        "Position %d%c is not possible".format(number, letter)
    )
}