package com.romulo.chess

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ChessApplication

fun main(args: Array<String>) {
    SpringApplication.run(ChessApplication::class.java, *args)
}