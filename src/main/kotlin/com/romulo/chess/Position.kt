package com.romulo.chess

data class Position(val number: Int, val letter: Char) {
    init {
        if (number !in 1..8 || letter.toInt() !in 97..105) {
            throw IllegalArgumentException("Invalid position")
        }
    }

    fun isAt(number: Int, letter: Char) : Boolean = this.number.equals(number) && this.letter.equals(letter)

    fun isStraightForward(other: Position): Boolean = this.letter == other.letter && other.number > this.number
    fun isStraightBackward(other: Position): Boolean = this.letter == other.letter && other.number < this.number

    fun distanceTo(other: Position): Int {
        if (isStraightForward(other) || isStraightBackward(other)) {
            return Math.abs(this.number - other.number)
        }

        return 0
    }
}