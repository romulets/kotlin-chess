package com.romulo.chess.domain

import org.springframework.data.mongodb.repository.MongoRepository

interface GameBoardRepository : MongoRepository<GameBoard, String>