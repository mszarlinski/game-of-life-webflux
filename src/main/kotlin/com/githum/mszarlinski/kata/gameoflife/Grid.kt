package com.githum.mszarlinski.kata.gameoflife

import java.util.concurrent.ThreadLocalRandom

internal class Grid(val width: Int, val height: Int) {

    private val cells: MutableList<Cell> = MutableList(width * height, { Cell.random() })

    fun aliveCells(): Int = cells.count { it.isAlive() }

    fun deadCells(): Int = cells.count { it.isDead() }

    fun cellAt(i: Int, j: Int): Cell =
            if (outOfBounds(i, j)) Cell.DEAD else cells[i * width + j]

    private fun outOfBounds(i: Int, j: Int) = i < 0 || i >= height || j < 0 || j >= width

    fun setCellAt(i: Int, j: Int, value: Boolean) {
        cells[i * width + j] = Cell(value)
    }

    data class Cell(private var value: Boolean) {
        fun isAlive() = value
        fun isDead() = !isAlive()

        companion object {
            fun random(): Cell = Cell(ThreadLocalRandom.current().nextBoolean())

            val DEAD = Cell(false)
        }
    }
}
