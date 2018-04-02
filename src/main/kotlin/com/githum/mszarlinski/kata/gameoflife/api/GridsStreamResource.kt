package com.githum.mszarlinski.kata.gameoflife.api

import com.githum.mszarlinski.kata.gameoflife.domain.GameOfLife
import com.githum.mszarlinski.kata.gameoflife.domain.Grid
import org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.temporal.ChronoUnit

@RestController
internal class GridsStreamResource(
        private val gameOfLife: GameOfLife
) {

    @GetMapping("/grids", produces = [TEXT_EVENT_STREAM_VALUE])
    fun gridsStream(
            @RequestParam("w") width: Int,
            @RequestParam("h") height: Int,
            @RequestParam("limit", required = false, defaultValue = "10") limit: Long): Flux<List<String>> {
        var currentGen = gameOfLife.start(width, height)
        return Flux.interval(Duration.of(1, ChronoUnit.SECONDS))
                .map {
                    val result = currentGen
                    currentGen = gameOfLife.calculateNextGeneration(currentGen)
                    result.dump()
                }
                .take(limit)
    }

    private fun Grid.dump() = cells.map { if (it.isAlive()) "A" else "D" }
}

