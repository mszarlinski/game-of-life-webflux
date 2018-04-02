package com.githum.mszarlinski.kata.gameoflife

import java.util.concurrent.ThreadLocalRandom

internal class Grid(val width: Int, val height: Int) {

    private val cells: MutableList<Cell> = MutableList(width * height, { Cell.random() })

    fun aliveCells(): Int = cells.count { it.isAlive() }

    fun deadCells(): Int = cells.count { it.isDead() }

    fun cellAt(coord: Coord): Cell =
            if (outOfBounds(coord)) Cell.DEAD else cells[coord.height * width + coord.width]

    private fun outOfBounds(coord: Coord) = coord.height < 0 || coord.height >= height || coord.width < 0 || coord.width >= width

    fun setCellAt(coord: Coord, value: Boolean) {
        cells[coord.height * width + coord.width] = Cell(value)
    }

    fun forEach(action: (Cell, Coord) -> Unit) {
        for (i in 0 until height) {
            for (j in 0 until width) {
                action(cells[i * width + j], Coord(i, j))
            }
        }
    }

    data class Cell(private val value: Boolean) {
        fun isAlive() = value
        fun isDead() = !isAlive()

        companion object {
            fun random(): Cell = Cell(ThreadLocalRandom.current().nextBoolean())

            val DEAD = Cell(false)
        }
    }

    data class Coord(val height: Int, val width: Int) {
        fun shift(delta: Pair<Int, Int>) = Coord(height + delta.first, width + delta.second)

        companion object {
            @JvmStatic
            fun of(height: Int, width: Int) = Coord(height, width)
        }
    }

}
