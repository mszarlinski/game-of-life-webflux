package com.githum.mszarlinski.kata.gameoflife.domain


internal class CellStateCalculator {

    fun calculateFor(grid: Grid, cell: Cell, coordinates: Coordinates): Cell.State {
        return if (cell.isDead()) {
            if (shouldBeRestored(grid, coordinates)) {
                Cell.State.ALIVE
            } else {
                Cell.State.DEAD
            }
        } else {
            if (!isUnderpopulated(grid, coordinates) && !isOvercrowded(grid, coordinates)) {
                Cell.State.ALIVE
            } else {
                Cell.State.DEAD
            }
        }
    }

    private fun isUnderpopulated(grid: Grid, coordinates: Coordinates): Boolean =
            grid.aliveNeighboursCountAround(coordinates) < 2

    private fun isOvercrowded(grid: Grid, coordinates: Coordinates): Boolean =
            grid.aliveNeighboursCountAround(coordinates) > 3

    private fun shouldBeRestored(grid: Grid, coordinates: Coordinates): Boolean =
            grid.aliveNeighboursCountAround(coordinates) == 3
}