package com.lk.mancala.game

import com.lk.mancala.game.readmodel.GameException
import spock.lang.Specification

class DataStructureSpec extends Specification implements MancalaGameTrait {

    def "in the selected pit should be zero stones and in next it should be +1 Player 1 should score one point"() {

        given:
        Game gameBoard = thereIsStandardGameSetup("player1", "player2")

        when:
        TurnCommand turnCommand = new TurnCommand("player1", 3)
        gameBoard.doTurn(turnCommand)

        then:
        gameBoard.playerStonesInPit("player1", 3) == 0
        and:
        gameBoard.playerStonesInPit("player1", 4) == 7
        and:
        gameBoard.getScores("player1") == 1
    }

    def "if there is a lot of stones it must add stone to home each time passing it"() {

        given:
        Game gameBoard = thereIsGameSetup("player1", "player2", 14)

        when:
        TurnCommand turnCommand = new TurnCommand("player1", 6)
        gameBoard.doTurn(turnCommand)

        then:
        gameBoard.getScores("player1") == 2

    }

    def "when one player did move he cant do another"() {

        given:
        Game gameBoard = thereIsStandardGameSetup("player1", "player2")

        when:
        TurnCommand turnCommand = new TurnCommand("player1", 3)
        gameBoard.doTurn(turnCommand)

        and:
        turnCommand = new TurnCommand("player1", 4)
        gameBoard.doTurn(turnCommand)

        then:
        thrown(GameException)
    }

    def "when player1 finish in own empty pit take all stones from player2 in opposite pit"() {

        given:
        Map<Integer, Pit> playerOneBoard = MancalaFixture.thereIsPlayeBoard(5, 0, 0, 0, 0, 0)
        Map<Integer, Pit> playerTwoBoard = MancalaFixture.thereIsPlayeBoard(5, 0, 0, 0, 0, 0)
        Game gameBoard = thereIsGameSetup("player1", "player2", playerOneBoard, playerTwoBoard)

        when:
        TurnCommand turnCommand = new TurnCommand("player1", 1)
        gameBoard.doTurn(turnCommand)

        then:
        gameBoard.getScores("player1") == 5
    }

    def "when player1 finish turn in the score point it has another turn"() {

        given:
        Map<Integer, Pit> playerOneBoard = MancalaFixture.thereIsPlayeBoard(6, 0, 0, 0, 2, 0)
        Map<Integer, Pit> playerTwoBoard = MancalaFixture.thereIsPlayeBoard(6, 0, 0, 0, 0, 0)
        Game gameBoard = thereIsGameSetup("player1", "player2", playerOneBoard, playerTwoBoard)

        when:
        TurnCommand turnCommand = new TurnCommand("player1", 5)
        gameBoard.doTurn(turnCommand)

        then:
        gameBoard.whichPlayerTurn() == "player1"
    }

    def "when player has no more moves all balls from opposite board are going into opposite player"() {

        given:
        Map<Integer, Pit> playerOneBoard = MancalaFixture.thereIsPlayeBoard(0, 0, 0, 0, 0, 0)
        Map<Integer, Pit> playerTwoBoard = MancalaFixture.thereIsPlayeBoard(6, 6, 6, 6, 6, 6)
        Game gameBoard = thereIsGameSetup("player1", "player2", playerOneBoard, playerTwoBoard)

        when:
        TurnCommand turnCommand = new TurnCommand("player1", 5)
        gameBoard.doTurn(turnCommand)

        then:
        gameBoard.getScores("player2") == 36
    }

    def "when playe1 do move correct pits are filled"() {

        given:
        Game gameBoard = thereIsStandardGameSetup("player1", "player2")

        when:
        TurnCommand turnCommand = new TurnCommand("player1", 5)
        gameBoard.doTurn(turnCommand)

        then:
        gameBoard.playerStonesInPit(player, pit) == count

        and:
        gameBoard.getScores(player) == score

        where:
        player    | pit | count | score
        "player1" | 6   | 7     | 1
        "player2" | 1   | 7     | 0
        "player2" | 2   | 7     | 0

    }

    def "when unknown player is trying to do the turn exception has to be thrown"(){

        given:
        Game gameBoard = thereIsStandardGameSetup("player1", "player2")

        when:
        TurnCommand turnCommand = new TurnCommand("player3", 5)
        gameBoard.doTurn(turnCommand)

        then:
        thrown(GameException)

    }


}
