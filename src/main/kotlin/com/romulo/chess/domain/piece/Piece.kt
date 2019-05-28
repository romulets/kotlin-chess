package com.romulo.chess.domain.piece

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.romulo.chess.domain.Color
import com.romulo.chess.domain.Position

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="type")
interface Piece {
    val color: Color
    val position: Position

    fun moveTo(position: Position) : Boolean
    fun possiblePositions(positionIsFull : (position : Position) -> Boolean): List<Position>
}