package com.lk.mancala.game.readmodel;

import static java.util.Collections.*;
import static java.util.stream.Collectors.*;

import java.util.Map;
import java.util.Map.Entry;

public class GameStatsData {

  private Map<String, Integer> scores;
  private GameStatus gameSatus;

  public GameStatsData(Map<String, Integer> scores, GameStatus gameSatus) {
    this.scores = scores;
    this.gameSatus = gameSatus;
  }

  public Map<String, Integer> getScores() {
    return unmodifiableMap(scores);
  }

  public GameStatus getGameSatus() {
    return gameSatus;
  }

  public GameStatsData endGame() {
    return new GameStatsData(scores, GameStatus.ENDED);
  }

  public GameStatsData addScores(int scoresToAdd, String player) {
    Map<String, Integer> updatedMap = scores.entrySet()
        .stream()
        .collect(toMap(
            Entry::getKey, o -> addScores(scoresToAdd, o.getValue(), o.getKey(), player)));

    return new GameStatsData(updatedMap, gameSatus);
  }

  private Integer addScores(int scoresToAdd, int existingScores, String player,
      String playerToCompare) {
    if (playerToCompare.equals(player)) {
      return scoresToAdd + existingScores;
    }
    return existingScores;
  }
}
