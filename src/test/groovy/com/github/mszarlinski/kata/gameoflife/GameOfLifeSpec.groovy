package com.github.mszarlinski.kata.gameoflife

import com.githum.mszarlinski.kata.gameoflife.domain.CellStateCalculator
import com.githum.mszarlinski.kata.gameoflife.domain.Coordinates
import com.githum.mszarlinski.kata.gameoflife.domain.GameOfLife
import com.githum.mszarlinski.kata.gameoflife.domain.Grid
import spock.lang.Specification

import static com.github.mszarlinski.kata.gameoflife.SampleGrid.O
import static com.github.mszarlinski.kata.gameoflife.SampleGrid.X
import static com.github.mszarlinski.kata.gameoflife.SampleGrid.aGrid

class GameOfLifeSpec extends Specification {

    GameOfLife gameOfLife = new GameOfLife(new CellStateCalculator())

    def "You start with a two dimensional grid of cells, where each cell is either alive or dead"() {
        when:
            Grid grid = gameOfLife.start(3, 2)

        then:
            grid.width == 3
            grid.height == 2

        and:
            grid.aliveCells() + grid.deadCells() == 3 * 2
    }

    def "Program can accept an arbitrary grid of cells, and will output a similar grid showing the next generation"() {
        when:
            Grid nextGenGrid = gameOfLife.calculateNextGeneration(new Grid(width, height))

        then:
            nextGenGrid.width == width
            nextGenGrid.height == height

        where:
            width | height
            1     | 2
            3     | 1
    }

    def "The grid is finite, and no life can exist off the edges"() {
        when:
            Grid grid = aGrid(
                    [
                            [O, O, O],
                            [O, O, O]
                    ]
            )

        then:
            grid.cellAt(Coordinates.of(i, j)).isDead()

        where: "index is out of bounds"
            i  | j
            0  | 3
            2  | 1
            -1 | 0
            1  | -2
            5  | 5
    }

    def "Any live cell with fewer than two live neighbours dies, as if caused by underpopulation"() {
        given:
            Grid grid = aGrid(
                    [
                            [O, X, X],
                            [X, O, O]
                    ]
            )

        when:
            Grid nextGenGrid = gameOfLife.calculateNextGeneration(grid)

        then:
            nextGenGrid.cellAt(Coordinates.of(0, 0)).isDead()
            nextGenGrid.cellAt(Coordinates.of(1, 2)).isDead()

        and:
            nextGenGrid.cellAt(Coordinates.of(1, 1)).isAlive()
    }

    def "Any live cell with more than three live neighbours dies, as if by overcrowding"() {
        given:
            Grid grid = aGrid(
                    [
                            [O, X, O],
                            [O, O, O]
                    ]
            )

        when:
            Grid nextGenGrid = gameOfLife.calculateNextGeneration(grid)

        then:
            nextGenGrid.cellAt(Coordinates.of(1, 1)).isDead()

        and:
            nextGenGrid.cellAt(Coordinates.of(1, 0)).isAlive()
            nextGenGrid.cellAt(Coordinates.of(0, 2)).isAlive()
    }

    def "Any dead cell with exactly three live neighbours becomes a live cell"() {
        given:
            Grid grid = aGrid(
                    [
                            [O, X, O],
                            [X, O, O],
                            [O, X, X]
                    ]
            )

        when:
            Grid nextGenGrid = gameOfLife.calculateNextGeneration(grid)

        then:
            nextGenGrid.cellAt(Coordinates.of(1, 0)).isAlive()
            nextGenGrid.cellAt(Coordinates.of(2, 1)).isAlive()

        and:
            nextGenGrid.cellAt(Coordinates.of(0, 1)).isDead()
    }
}
