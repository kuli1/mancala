package com.lk.mancala.game.events;

import java.util.UUID;

public class GameEndEvent {

  UUID gameId;

  public GameEndEvent(UUID gameId) {
    this.gameId = gameId;
  }

  public UUID getGameId() {
    return gameId;
  }
}
