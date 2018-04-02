package com.githum.mszarlinski.kata.gameoflife


internal data class Coordinates(val height: Int, val width: Int) {
    fun shift(delta: Pair<Int, Int>) = Coordinates(height + delta.first, width + delta.second)

    companion object {
        @JvmStatic
        fun of(height: Int, width: Int) = Coordinates(height, width)
    }
}