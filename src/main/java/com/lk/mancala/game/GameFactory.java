package com.lk.mancala.game;

import static com.lk.mancala.game.PlayerBoard.*;
import static java.util.Map.*;

import com.lk.mancala.game.events.GameStartedEvent;

public class GameFactory {

  private final GameEvents gameEvents;

  public GameFactory(GameEvents gameEvents) {
    this.gameEvents = gameEvents;
  }

  public Game startGameFor(String player1Name, String player2Name) {

    Player player1 = new Player(player1Name, standardBoard());
    Player player2 = new Player(player2Name, standardBoard());
    Game game = new Game(of(player1.getName(), player1, player2.getName(), player2), player1,
        standardBoard().maxNumberOfPits(), gameEvents);
    gameEvents.emit(new GameStartedEvent(game.gemeId(), player1Name, player2Name));
    return game;
  }

}
