package com.githum.mszarlinski.kata.gameoflife

import java.util.concurrent.ThreadLocalRandom

internal class Grid(n: Int, m: Int) {

    private val cells: List<Cell> = List(n * m, { Cell.random() })

    fun aliveCells(): Int = cells.count { it.isAlive() }

    fun deadCells(): Int = cells.count { it.isDead() }

    data class Cell(private var value: Boolean) {
        fun isAlive() = value
        fun isDead() = !isAlive()

        companion object {
            fun random(): Cell = Cell(ThreadLocalRandom.current().nextBoolean())
        }
    }
}
