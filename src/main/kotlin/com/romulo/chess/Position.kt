package com.romulo.chess

data class Position(val number: Int, val letter: Char) {
    init {
        if (number !in 1..8 || letter.toInt() !in 97..105) {
            throw IllegalArgumentException("Invalid position")
        }
    }
}