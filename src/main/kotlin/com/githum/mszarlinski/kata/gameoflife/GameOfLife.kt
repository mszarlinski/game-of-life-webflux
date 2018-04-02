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
            shouldBeRestored(grid, coord)
        } else {
            !isUnderpopulated(grid, coord) && !isOvercrowded(grid, coord)
        }
    }

    private fun isUnderpopulated(grid: Grid, coord: Grid.Coord): Boolean =
            aliveNeighbours(grid, coord).count() < 2

    private fun isOvercrowded(grid: Grid, coord: Grid.Coord): Boolean =
            aliveNeighbours(grid, coord).count() > 3

    private fun shouldBeRestored(grid: Grid, coord: Grid.Coord): Boolean =
            aliveNeighbours(grid, coord).count() == 3

    private fun aliveNeighbours(grid: Grid, coord: Grid.Coord) =
            (-1..1).flatMap { first ->
                (-1..1).map { (first to it) }
            }
                    .filterNot { it.first == 0 && it.second == 0 }
                    .filter { grid.cellAt(coord.shift(it)).isAlive() }
}
