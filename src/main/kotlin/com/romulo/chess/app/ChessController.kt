package com.romulo.chess.app

import com.romulo.chess.domain.GameBoard
import com.romulo.chess.domain.position
import org.springframework.web.bind.annotation.*

@RestController()
class ChessController {

    @PostMapping("chess")
    fun newGame(): GameBoard = GameBoard()

    @PutMapping("chess/from/{fromNumber}/{fromLetter}/to/{toNumber}/{toLetter}")
    fun movePiece(
        @RequestBody gameBoard: GameBoard,
        @PathVariable("fromNumber") fromNumber: Int,
        @PathVariable("fromLetter") fromLetter: Char,
        @PathVariable("toNumber") toNumber: Int,
        @PathVariable("toLetter") toLetter: Char
    ): GameBoard {
        gameBoard.movePieceFromTo(
            position(fromNumber, fromLetter),
            position(toNumber, toLetter)
        )

        return gameBoard
    }

}