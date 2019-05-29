package com.romulo.chess.domain

import com.romulo.chess.domain.Color.BLACK
import com.romulo.chess.domain.Color.WHITE
import com.romulo.chess.domain.piece.*
import org.bson.types.ObjectId
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
class GameBoard {

    @Id
    lateinit var id: String

    @CreatedDate
    lateinit var createdAt: LocalDateTime

    @LastModifiedDate
    lateinit var updatedAt: LocalDateTime

    var player: Color = WHITE
    var pieces: MutableList<Piece> = ArrayList()
    var eatenPieces: MutableList<Piece> = ArrayList()

    init {
        pieces.add(Rook(WHITE, Position(1, 'a')))
        pieces.add(Knight(WHITE, Position(1, 'b')))
        pieces.add(Bishop(WHITE, Position(1, 'c')))
        pieces.add(Queen(WHITE, Position(1, 'd')))
        pieces.add(King(WHITE, Position(1, 'e')))
        pieces.add(Bishop(WHITE, Position(1, 'f')))
        pieces.add(Knight(WHITE, Position(1, 'g')))
        pieces.add(Rook(WHITE, Position(1, 'h')))
        setUpPawns(2, WHITE)

        setUpPawns(7, BLACK)
        pieces.add(Rook(BLACK, Position(8, 'a')))
        pieces.add(Knight(BLACK, Position(8, 'b')))
        pieces.add(Bishop(BLACK, Position(8, 'c')))
        pieces.add(Queen(BLACK, Position(8, 'd')))
        pieces.add(King(BLACK, Position(8, 'e')))
        pieces.add(Bishop(BLACK, Position(8, 'f')))
        pieces.add(Knight(BLACK, Position(8, 'g')))
        pieces.add(Rook(BLACK, Position(8, 'h')))
    }

    private fun setUpPawns(number: Int, color: Color) {
        for (letter in MIN_POSITION_LETTER.until(MAX_POSITION_LETTER)) {
            pieces.add(
                Pawn(
                    color,
                    Position(number, letter.toChar())
                )
            )
        }
    }

    fun pieceAt(position: Position): Piece? {
        return pieces.firstOrNull { p -> p.position.sameThat(position) }
    }

    fun play(from: Position, to: Position) {
        val piece = pieceAt(from) ?: throw IllegalArgumentException("Empty square")
        val eatenPiece = pieceAt(to)

        val couldMove = piece.moveTo(to, this::pieceAt)

        if (!couldMove) {
            throw IllegalArgumentException("Invalid play")
        }

        eatenPiece?.let {
            pieces.remove(eatenPiece)
            eatenPieces.add(eatenPiece)
        }

        changePlayers()
    }

    private fun changePlayers() {
        player = if (player == WHITE) {
            BLACK
        } else {
            WHITE
        }
    }

    fun possiblePlays(position: Position): List<Position> {
        val piece = pieceAt(position)
        if (piece != null) {
            return piece.possiblePositions(this::pieceAt)
        }

        throw IllegalArgumentException("empty position")
    }

}
