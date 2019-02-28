package com.lk.mancala.game.readmodel;

import java.util.UUID;

public interface GameStatsRepository {

  GameStatsData readGameStats(UUID gameId);
  void updateGameStats(UUID gameId,GameStatsData gameStatsData);

}
