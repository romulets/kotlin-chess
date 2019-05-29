package com.romulo.chess.domain

data class Position(
    val number: Int,
    val letter: Char
) {

    init {
        if (!validPosition(number, letter)) {
            throw IllegalArgumentException("Invalid position")
        }
    }

    fun add(number: Int, letter: Int): Position? {
        val newNumber = this.number + number
        val newLetter = this.letter.plus(letter)

        if (!validPosition(newNumber, newLetter)) {
            return null
        }

        return Position(newNumber, newLetter)
    }

    fun addNumber(number: Int): Position? = add(number, 0)

    fun sameThat(other: Position): Boolean = this.number == other.number && this.letter == other.letter
}

const val MIN_POSITION_NUMBER: Int = 1
const val MAX_POSITION_NUMBER: Int = 8
const val MIN_POSITION_LETTER: Int = 97
const val MAX_POSITION_LETTER: Int = 105

fun validPosition(number: Int, letter: Char): Boolean {
    return number in MIN_POSITION_NUMBER..MAX_POSITION_NUMBER
            && letter.toInt() in MIN_POSITION_LETTER..MAX_POSITION_LETTER
}