package com.lk.mancala.infrastructure;

import com.lk.mancala.game.events.GameEnded;
import com.lk.mancala.game.GameEvents;
import com.lk.mancala.game.events.PointsScored;
import com.lk.mancala.game.events.GameStarted;
import com.lk.mancala.game.events.TurnDone;
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
  public void emit(GameStarted gameStarted) {

    GameStatsData newGameData = new GameStatsData(
        Map.of(gameStarted.getPlayer1Name(), 0, gameStarted.getPlayer2Name(), 0),
        GameStatus.STARTED);
    gameStatsRepository.updateGameStats(gameStarted.getGameId(), newGameData);
  }

  @Override
  public void emit(TurnDone turnDone) {

  }

  @Override
  public void emit(GameEnded gameEnded) {
    GameStatsData gameStatsData = gameStatsRepository.readGameStats(gameEnded.getGameId());
    GameStatsData endedGame = gameStatsData.endGame();
    gameStatsRepository.updateGameStats(gameEnded.getGameId(), endedGame);
  }

  @Override
  public void emit(PointsScored pointsScored) {
    GameStatsData gameStatsData = gameStatsRepository.readGameStats(pointsScored.getGameId());
    GameStatsData newGameStats = gameStatsData
        .addScores(pointsScored.getScoresToAdd(), pointsScored.getPlayerName());

    gameStatsRepository.updateGameStats(pointsScored.getGameId(), newGameStats);
  }
}
