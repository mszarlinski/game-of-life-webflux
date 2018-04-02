package com.github.mszarlinski.kata.gameoflife

import kotlin.Pair
import spock.lang.Specification

import static com.githum.mszarlinski.kata.gameoflife.Grid.Coord

class CoordSpec extends Specification {

    def "should shift coordinates"() {
        given:
            Coord coordinates = Coord.of(1, 2)

        when:
            Coord shifted = coordinates.shift(new Pair(height, width))

        then:
            shifted.height == expHeight
            shifted.width == expWidth

        where:
            height | width || expHeight | expWidth
            -1     | 1     || 0         | 3
            2      | 0     || 3         | 2
            0      | -2    || 1         | 0
            -3     | -3    || -2        | -1
    }
}
