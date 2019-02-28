package com.lk.mancala.infrastructure;

import com.lk.mancala.game.events.GameEndEvent;
import com.lk.mancala.game.GameEvents;
import com.lk.mancala.game.events.ScoredEvent;
import com.lk.mancala.game.events.GameStartedEvent;
import com.lk.mancala.game.events.TurnDoneEvent;
import com.lk.mancala.game.readmodel.GameStatsData;
import com.lk.mancala.game.readmodel.GameStatsRepository;
import com.lk.mancala.game.readmodel.GameStatus;
import java.util.Map;

public class InMemoryGameEventPropagator implements GameEvents {

  private final GameStatsRepository gameStatsRepository;

  public InMemoryGameEventPropagator(
      GameStatsRepository gameStatsRepository) {
    this.gameStatsRepository = gameStatsRepository;
  }

  @Override
  public void emit(GameStartedEvent gameStartedEvent) {

    GameStatsData newGameData = new GameStatsData(
        Map.of(gameStartedEvent.getPlayer1Name(), 0, gameStartedEvent.getPlayer2Name(), 0),
        GameStatus.STARTED);
    gameStatsRepository.updateGameStats(gameStartedEvent.getGameId(), newGameData);
  }

  @Override
  public void emit(TurnDoneEvent turnDoneEvent) {

  }

  @Override
  public void emit(GameEndEvent gameEndEvent) {
    GameStatsData gameStatsData = gameStatsRepository.readGameStats(gameEndEvent.getGameId());
    GameStatsData endedGame = gameStatsData.endGame();
    gameStatsRepository.updateGameStats(gameEndEvent.getGameId(), endedGame);
  }

  @Override
  public void emit(ScoredEvent scoredEvent) {
    GameStatsData gameStatsData = gameStatsRepository.readGameStats(scoredEvent.getGameId());
    GameStatsData newGameStats = gameStatsData
        .addScores(scoredEvent.getScoresToAdd(), scoredEvent.getPlayerName());

    gameStatsRepository.updateGameStats(scoredEvent.getGameId(), newGameStats);
  }
}
