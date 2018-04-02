package com.github.mszarlinski.kata.gameoflife

import com.githum.mszarlinski.kata.gameoflife.GameOfLife
import com.githum.mszarlinski.kata.gameoflife.Grid
import spock.lang.Specification


class GameOfLifeSpec extends Specification {

    GameOfLife gameOfLife = new GameOfLife()

    def "You start with a two dimensional grid of cells, where each cell is either alive or dead"() {
        when:
            Grid grid = gameOfLife.start(3, 2)

        then:
            grid.aliveCells() + grid.deadCells() == 3 * 2
    }

    def "Program can accept an arbitrary grid of cells, and will output a similar grid showing the next generation"() {

    }

    def "The grid is finite, and no life can exist off the edges"() {

    }

    def "Any live cell with fewer than two live neighbours dies, as if caused by underpopulation"() {

    }

    def "Any live cell with more than three live neighbours dies, as if by overcrowding"() {

    }

    def "Any live cell with two or three live neighbours lives on to the next generation"() {

    }

    def "Any dead cell with exactly three live neighbours becomes a live cell"() {

    }
}
