package com.githum.mszarlinski.kata.gameoflife


internal class GameOfLife {

    fun start(width: Int, height: Int) = Grid(width, height)

    fun calculateNextGeneration(current: Grid): Grid =
            Grid(current.width, current.height).apply {
                current.forEach { cell, coord ->
                    this.setCellAt(coord, calculateCellState(current, cell, coord))
                }
            }

    private fun calculateCellState(grid: Grid, cell: Cell, coordinates: Coordinates): Boolean {
        return if (cell.isDead()) {
            shouldBeRestored(grid, coordinates)
        } else {
            !isUnderpopulated(grid, coordinates) && !isOvercrowded(grid, coordinates)
        }
    }

    private fun isUnderpopulated(grid: Grid, coordinates: Coordinates): Boolean =
            grid.aliveNeighboursCountAround(coordinates) < 2

    private fun isOvercrowded(grid: Grid, coordinates: Coordinates): Boolean =
            grid.aliveNeighboursCountAround(coordinates) > 3

    private fun shouldBeRestored(grid: Grid, coordinates: Coordinates): Boolean =
            grid.aliveNeighboursCountAround(coordinates) == 3
}
