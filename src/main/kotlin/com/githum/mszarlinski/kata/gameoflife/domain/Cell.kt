package com.githum.mszarlinski.kata.gameoflife.domain

import java.util.concurrent.ThreadLocalRandom

internal data class Cell(private val state: State) {

    enum class State {
        ALIVE, DEAD;
    }

    fun isAlive() = state == State.ALIVE
    fun isDead() = !isAlive()

    companion object {
        fun random(): Cell = Cell(if (ThreadLocalRandom.current().nextBoolean()) State.ALIVE else State.DEAD)

        val DEAD_CELL = Cell(State.DEAD)
    }
}
