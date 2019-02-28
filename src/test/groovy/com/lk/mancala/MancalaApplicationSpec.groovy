package com.lk.mancala

import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MancalaApplicationSpec extends Specification {


    def "when context is loaded then all expected beans are created"() {
        expect: "the WebController is created"
    }

}
