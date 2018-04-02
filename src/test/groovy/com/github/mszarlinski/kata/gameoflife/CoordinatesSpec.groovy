package com.github.mszarlinski.kata.gameoflife

import com.githum.mszarlinski.kata.gameoflife.Coordinates
import kotlin.Pair
import spock.lang.Specification

class CoordinatesSpec extends Specification {

    def "should shift coordinates"() {
        given:
            Coordinates coordinates = Coordinates.of(1, 2)

        when:
            Coordinates shifted = coordinates.shift(new Pair(height, width))

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
