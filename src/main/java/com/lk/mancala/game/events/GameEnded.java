package com.lk.mancala.game.events;

import java.util.UUID;

public class GameEnded {

  UUID gameId;

  public GameEnded(UUID gameId) {
    this.gameId = gameId;
  }

  public UUID getGameId() {
    return gameId;
  }
}
