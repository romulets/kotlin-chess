package com.romulo.chess.domain

import com.romulo.chess.domain.Color.BLACK
import com.romulo.chess.domain.Color.WHITE
import com.romulo.chess.domain.piece.Bishop
import com.romulo.chess.domain.piece.King
import com.romulo.chess.domain.piece.Knight
import com.romulo.chess.domain.piece.Pawn
import com.romulo.chess.domain.piece.Piece
import com.romulo.chess.domain.piece.Queen
import com.romulo.chess.domain.piece.Rook
import com.romulo.chess.domain.piece.opponentPieces
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

    var status: GameStatus = GameStatus.OnGoing

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
        for (letter in MIN_POSITION_LETTER.rangeTo(MAX_POSITION_LETTER)) {
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
        val possibleEatenPiece = pieceAt(to)

        movePiece(piece, to)
        eatPiece(possibleEatenPiece)

        if (isCurrentKingInCheck()) {
            throw IllegalArgumentException("You can't let your king in check")
        }

        changePlayers()

        if (isCurrentKingInCheck()) {
            this.status = GameStatus.Check
        } else {
            this.status = GameStatus.OnGoing
        }

    }

    private fun isCurrentKingInCheck(): Boolean {
        val king = pieces.find { piece -> piece is King && piece.color == player }!!
        val opponentPieces = pieces.filter { piece -> piece.color != player }
        return opponentPieces.any { piece -> piece.possiblePositions(this::pieceAt).contains(king.position) }
    }

    private fun eatPiece(eatenPiece: Piece?) {
        eatenPiece?.let {
            pieces.remove(eatenPiece)
            eatenPieces.add(eatenPiece)
        }
    }

    private fun movePiece(piece: Piece, to: Position) {
        val couldMove = piece.moveTo(to, this::pieceAt)
        if (!couldMove) {
            throw IllegalArgumentException("Invalid play")
        }
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
