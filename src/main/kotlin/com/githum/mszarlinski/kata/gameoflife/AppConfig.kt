package com.githum.mszarlinski.kata.gameoflife

import com.githum.mszarlinski.kata.gameoflife.domain.CellStateCalculator
import com.githum.mszarlinski.kata.gameoflife.domain.GameOfLife
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
internal class AppConfig {

    @Bean
    fun gameOfLife() = GameOfLife(CellStateCalculator())
}