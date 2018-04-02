package com.githum.mszarlinski.kata.gameoflife

internal class Grid(val width: Int, val height: Int) {

    private val cells: MutableList<Cell> = MutableList(width * height, { Cell.random() })

    fun aliveCells(): Int = cells.count { it.isAlive() }

    fun deadCells(): Int = cells.count { it.isDead() }

    fun cellAt(coordinates: Coordinates): Cell =
            if (outOfBounds(coordinates)) Cell.DEAD else cells[coordinates.height * width + coordinates.width]

    private fun outOfBounds(coordinates: Coordinates) = coordinates.height < 0 || coordinates.height >= height || coordinates.width < 0 || coordinates.width >= width

    fun setCellAt(coordinates: Coordinates, value: Boolean) {
        cells[coordinates.height * width + coordinates.width] = Cell(value)
    }

    fun forEach(action: (Cell, Coordinates) -> Unit) {
        for (i in 0 until height) {
            for (j in 0 until width) {
                action(cells[i * width + j], Coordinates(i, j))
            }
        }
    }

    fun aliveNeighboursCountAround(coordinates: Coordinates): Int =
            (-1..1).flatMap { first ->
                (-1..1).map { (first to it) }
            }
                    .filterNot { it.first == 0 && it.second == 0 }
                    .filter { cellAt(coordinates.shift(it)).isAlive() }
                    .count()
}
