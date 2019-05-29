package com.romulo.chess.app

import com.romulo.chess.domain.ChessService
import com.romulo.chess.domain.GameBoard
import com.romulo.chess.domain.Position
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

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
        @PathVariable("letter") letter: Char
    ): List<Position> {
        return chessService.possiblePlays(id, Position(number, letter))
    }

}