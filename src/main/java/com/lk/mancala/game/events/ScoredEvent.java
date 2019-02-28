package com.lk.mancala.game.events;

import java.util.UUID;

public class ScoredEvent {

  private String playerName;
  private int scoresToAdd;
  private UUID gameId;


  public ScoredEvent(String playerName, int i, UUID gameId) {
      this.playerName = playerName;
      this.scoresToAdd = i;
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
