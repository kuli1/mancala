package com.lk.mancala.infrastructure;

import com.lk.mancala.game.readmodel.GameStatsData;
import com.lk.mancala.game.readmodel.GameStatsRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryGameStatsRepository implements GameStatsRepository {

  private Map<UUID, GameStatsData> gameStats = new HashMap<>();

  @Override
  public GameStatsData readGameStats(UUID gameId) {
    return gameStats.get(gameId);
  }

  @Override
  public void updateGameStats(UUID gameId, GameStatsData gameStatsData) {
    gameStats.put(gameId, gameStatsData);
  }
}
