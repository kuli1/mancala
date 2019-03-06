package com.lk.mancala.game.events;

import java.util.UUID;

public class PointsScored {

  private String playerName;
  private int scoresToAdd;
  private UUID gameId;


  public PointsScored(String playerName, int scoreToAdd, UUID gameId) {
      this.playerName = playerName;
      this.scoresToAdd = scoreToAdd;
      this.gameId = gameId;
  }

  public String getPlayerName() {
    return playerName;
  }

  public int getScoresToAdd() {
    return scoresToAdd;
  }

  public UUID getGameId() {
    return gameId;
  }
}
