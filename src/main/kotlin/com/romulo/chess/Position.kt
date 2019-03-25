package com.romulo.chess

data class Position(val number: Int, val letter: Char) {
    init {
        if (number !in 1..8 || letter.toInt() !in 97..105) {
            throw IllegalArgumentException("Invalid position")
        }
    }

    public fun isAt(number: Int, letter: Char) : Boolean = this.number.equals(number) && this.letter.equals(letter)
}