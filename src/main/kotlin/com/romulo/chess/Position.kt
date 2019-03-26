package com.romulo.chess

data class Position(
    val number: Int,
    val letter: Char,
    val full: Boolean = false
) {

    init {
        if (number !in 1..8 || letter.toInt() !in 97..105) {
            throw IllegalArgumentException("Invalid position")
        }
    }

    fun sameThat(other : Position) : Boolean = this.number == other.number && this.letter == other.letter

    fun isStraightForward(other: Position): Boolean = this.letter == other.letter && other.number > this.number

    fun isStraightBackward(other: Position): Boolean = this.letter == other.letter && other.number < this.number

    private fun isDiagonal(other: Position) : Boolean = Math.abs(this.number - other.number) ==  Math.abs(this.letter.toInt() - other.letter.toInt())

    fun isForwardDiagonal(other: Position): Boolean = isDiagonal(other) && other.number > this.number

    fun isBackwardDiagonal(other: Position): Boolean =isDiagonal(other) && other.number < this.number

    fun distanceTo(other: Position): Int = Math.abs(this.number - other.number)

    fun fullPosition() : Position = fullPosition(number, letter)

    fun emptyPosition() : Position = position(number, letter)
}

fun fullPosition(number: Int, letter: Char) : Position = Position(number, letter, full = true)
fun position(number: Int, letter: Char) : Position = Position(number, letter, full = false)
