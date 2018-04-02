package com.githum.mszarlinski.kata.gameoflife


internal class GameOfLife {

    fun start(width: Int, height: Int) = Grid(width, height)

    fun calculateNextGeneration(grid: Grid): Grid {
        val newGen = Grid(grid.width, grid.height)
        for (i in 0 until grid.height) {
            for (j in 0 until grid.width) {
                if (grid.cellAt(i, j).isDead()) {
                    newGen.setCellAt(i, j, false)
                } else {
                    if (isUnderpopulated(grid, i, j)) {
                        newGen.setCellAt(i, j, false)
                    } else {
                        newGen.setCellAt(i, j, true)
                    }
                }
            }
        }
        return newGen
    }

    private fun isUnderpopulated(grid: Grid, i: Int, j: Int): Boolean {
        val deltas = (-1..1)
                .flatMap { first ->
                    (-1..1).map { (first to it) }
                }
        return deltas
                .filter { (it.first == 0) xor (it.second == 0) }
                .filter { grid.cellAt(i + it.first, j + it.second).isAlive() }
                .count() < 2
    }

}