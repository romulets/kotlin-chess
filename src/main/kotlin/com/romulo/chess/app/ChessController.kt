package com.romulo.chess.app

import com.romulo.chess.domain.ChessService
import com.romulo.chess.domain.GameBoard
import com.romulo.chess.domain.Position
import org.springframework.web.bind.annotation.*

@RestController
class ChessController(private val chessService: ChessService) {

    @PostMapping("chess")
    fun newGame(): GameBoard = chessService.newGame()

    @PutMapping("chess/{id}")
    fun play(@PathVariable("id") id: String, @RequestBody play: Play): GameBoard {
        return chessService.play(id, play.from, play.to)
    }

    @GetMapping("chess/{id}/position/{number}/{letter}/possible-plays")
    fun possiblePlays(
        @PathVariable("id") id: String,
        @PathVariable("number") number: Int,
        @PathVariable("letter") letter: Char): List<Position> {
        return chessService.possiblePlays(id, Position(number, letter))
    }

}