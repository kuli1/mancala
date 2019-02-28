package com.lk.mancala.game;

import static java.util.Map.*;

import com.lk.mancala.game.events.GameStartedEvent;
import java.util.Map;

public class GameFactory {

  private final GameEvents gameEvents;

  public GameFactory(GameEvents gameEvents) {
    this.gameEvents = gameEvents;
  }

  public Game startGameFor(String player1Name, String player2Name) {
    Map<Integer, Pit> playerOneBoard = of(1, new Pit(6), 2, new Pit(6), 3, new Pit(6), 4,
        new Pit(6), 5, new Pit(6), 6, new Pit(6));
    Map<Integer, Pit> playerTwoBoard = of(1, new Pit(6), 2, new Pit(6), 3, new Pit(6), 4,
        new Pit(6), 5, new Pit(6), 6, new Pit(6));
    Player player1 = new Player(player1Name, new PlayerBoard(playerOneBoard));
    Player player2 = new Player(player2Name, new PlayerBoard(playerTwoBoard));
    Game game = new Game(of(player1.getName(), player1, player2.getName(), player2), player1,
        gameEvents);
    gameEvents.emit(new GameStartedEvent(game.gemeId(), player1Name, player2Name));
    return game;
  }

}
