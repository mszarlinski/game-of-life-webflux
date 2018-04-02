package com.github.mszarlinski.kata.gameoflife

import com.githum.mszarlinski.kata.gameoflife.AppConfig
import com.githum.mszarlinski.kata.gameoflife.AppRunnerKt
import com.githum.mszarlinski.kata.gameoflife.api.GridsStreamResource
import com.githum.mszarlinski.kata.gameoflife.domain.GameOfLife
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.FluxExchangeResult
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.test.StepVerifier
import spock.lang.Specification

import static org.springframework.http.MediaType.TEXT_EVENT_STREAM

@SpringBootTest(classes = [AppRunnerKt, AppConfig])
@ContextConfiguration
class GridsStreamResourceSpec extends Specification {

    @Autowired
    GameOfLife gameOfLife

    WebTestClient webClient

    def setup() {
        webClient = WebTestClient.bindToController(new GridsStreamResource(gameOfLife)).build()
    }

    def "should emit some grids"() {
        given:
            int numOfGenerations = 2

        when:
            FluxExchangeResult<List<String>> result = webClient.get()
                    .uri("/grids?w=1&h=1&limit=$numOfGenerations")
                    .exchange()
                    .expectStatus().isOk()
                    .expectHeader().contentType(TEXT_EVENT_STREAM)
                    .returnResult(List)

        then:
            StepVerifier.create(result.getResponseBody())
                    .expectNextCount(numOfGenerations)
                    .expectComplete()
                    .verify()
    }
}
