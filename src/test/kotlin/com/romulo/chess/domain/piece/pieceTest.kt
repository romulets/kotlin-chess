package com.romulo.chess.domain.piece

import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position

fun returnPawnIf(condition: (position: Position) -> Boolean, color: Color): (position: Position) -> Piece? {
    return { p -> if (condition(p)) Pawn(color, p) else null }
}