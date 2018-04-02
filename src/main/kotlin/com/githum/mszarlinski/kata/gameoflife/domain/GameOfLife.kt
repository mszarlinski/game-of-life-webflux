package com.githum.mszarlinski.kata.gameoflife.domain


internal class GameOfLife(
        private val cellStateCalculator: CellStateCalculator
) {

    fun start(width: Int, height: Int) = Grid(width, height)

    fun calculateNextGeneration(current: Grid): Grid =
            Grid(current.width, current.height).apply {
                current.forEach { cell, coord ->
                    this.setCellAt(coord, cellStateCalculator.calculateFor(current, cell, coord))
                }
            }
}
