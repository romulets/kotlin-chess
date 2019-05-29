package com.romulo.chess.domain

import org.springframework.stereotype.Component

@Component
class ChessService(
    private val gameBoardRepository: GameBoardRepository
) {

    fun newGame(): GameBoard {
        val gameBoard = GameBoard()
        gameBoardRepository.save(gameBoard)
        return gameBoard
    }

    fun play(gameId: String, from: Position, to: Position): GameBoard {
        val gameBoard = gameBoardRepository.findById(gameId).orElseThrow()
        gameBoard.play(from, to)
        gameBoardRepository.save(gameBoard)
        return gameBoard
    }

    fun possiblePlays(gameId: String, position: Position): List<Position> {
        val gameBoard = gameBoardRepository.findById(gameId).orElseThrow()
        return gameBoard.possiblePlays(position)
    }

}