package com.lk.mancala.game

import com.lk.mancala.game.readmodel.GameStatsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class GameSpecIT extends Specification {

    @Autowired
    GameService gameService

    @Autowired
    GameStatsRepository gameStatsRepository

    def "when player score it should be visible in the read model"() {
        given:
        def gameid = gameService.startNewGame("lukasz", "adam")

        when:
        gameService.playerMove(gameid, "lukasz", 5)

        then:
        def stats = gameStatsRepository.readGameStats(gameid)
        stats.scores.get("lukasz") == 1
        and:
        stats.scores.get("adam") == 0

    }


}
