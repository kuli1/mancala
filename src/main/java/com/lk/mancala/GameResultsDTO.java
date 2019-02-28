package com.lk.mancala;

import java.util.Map;

public class GameResultsDTO {

  private Map<String, Integer> gameResults;

  public GameResultsDTO(Map<String, Integer> gameResults) {
    this.gameResults = gameResults;
  }

  String getPlayerPoints(String playerName) {
    return String.format("Player : %s Has %s points", playerName, gameResults.get(playerName));
  }

}
