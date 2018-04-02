package com.githum.mszarlinski.kata.gameoflife


internal class GameOfLife {

    fun start(width: Int, height: Int) = Grid(width, height)

    fun calculateNextGeneration(current: Grid): Grid =
            Grid(current.width, current.height).apply {
                current.forEach { cell, coord ->
                    this.setCellAt(coord, calculateCellState(current, cell, coord))
                }
            }

    private fun calculateCellState(grid: Grid, cell: Grid.Cell, coord: Grid.Coord): Boolean {
        return if (cell.isDead()) {
            false
        } else {
            !isUnderpopulated(grid, coord)
        }
    }

    private fun isUnderpopulated(grid: Grid, coord: Grid.Coord): Boolean {
        val deltas = (-1..1)
                .flatMap { first ->
                    (-1..1).map { (first to it) }
                }
        return deltas
                .filter { (it.first == 0) xor (it.second == 0) }
                .filter { grid.cellAt(coord.shift(it)).isAlive() }
                .count() < 2
    }

}