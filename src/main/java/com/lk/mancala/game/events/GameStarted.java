package com.lk.mancala.game.events;

import java.util.UUID;

public class GameStarted {

  private UUID gameId;
  private String player1Name;
  private String player2Name;

  public GameStarted(UUID gameId, String player1Name, String player2Name) {
    this.gameId = gameId;
    this.player1Name = player1Name;
    this.player2Name = player2Name;
  }

  public UUID getGameId() {
    return gameId;
  }

  public String getPlayer1Name() {
    return player1Name;
  }

  public String getPlayer2Name() {
    return player2Name;
  }
}
