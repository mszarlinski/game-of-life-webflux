package com.githum.mszarlinski.kata.gameoflife

import java.util.concurrent.ThreadLocalRandom

internal data class Cell(private val value: Boolean) {
    fun isAlive() = value
    fun isDead() = !isAlive()

    companion object {
        fun random(): Cell = Cell(ThreadLocalRandom.current().nextBoolean())

        val DEAD = Cell(false)
    }
}
