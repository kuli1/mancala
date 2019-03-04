package com.lk.mancala.game

import com.lk.mancala.game.events.GameEndEvent
import com.lk.mancala.game.events.GameStartedEvent
import com.lk.mancala.game.events.ScoredEvent
import com.lk.mancala.game.events.TurnDoneEvent

import static java.util.Map.*

trait MancalaGameTrait {

    Game thereIsStandardGameSetup(String player1Name, String player2Name){
        return thereIsGameSetup(player1Name, player2Name, 6)
    }

    Game thereIsGameSetup(String player1Name, String player2Name, int numberOfStones){
        Map<Integer, Pit> playerOneBoard = MancalaFixture.thereIsPlayeBoard(numberOfStones,numberOfStones,numberOfStones,numberOfStones,numberOfStones,numberOfStones)
        Map<Integer, Pit> playerTwoBoard =   MancalaFixture.thereIsPlayeBoard(numberOfStones,numberOfStones,numberOfStones,numberOfStones,numberOfStones,numberOfStones)
        return thereIsGameSetup(player1Name,player2Name,playerOneBoard, playerTwoBoard)
    }

    Game thereIsGameSetup(String player1Name, String player2Name, Map<Integer, Pit> playerOneBoard, Map<Integer, Pit> playerTwoBoard){
        def (Player player1, Player player2) = setupPlayers(playerOneBoard, playerTwoBoard, player1Name, player2Name)
        return new Game(of(player1.getName(), player1, player2.getName(), player2), player1, 6, new TestGameEventPropagator())
    }

    private List setupPlayers(Map<Integer, Pit> playerOneBoard, Map<Integer, Pit> playerTwoBoard, String player1Name, String player2Name) {
        PlayerBoard playerBoard1 = new PlayerBoard(playerOneBoard)
        PlayerBoard playerBoard2 = new PlayerBoard(playerTwoBoard)
        Player player1 = new Player(player1Name, playerBoard1)
        Player player2 = new Player(player2Name, playerBoard2)
        [player1, player2]
    }

    private static class TestGameEventPropagator implements GameEvents{
        @Override
        void emit(GameStartedEvent gameStartedEvent) {

        }

        @Override
        void emit(TurnDoneEvent turnDoneEvent) {

        }

        @Override
        void emit(GameEndEvent gameEndEvent) {

        }

        @Override
        void emit(ScoredEvent scoredEvent) {

        }
    }

}