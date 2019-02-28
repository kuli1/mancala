package com.lk.mancala.game;

import java.util.Objects;

class Player {

  private String name;
  private int score;
  private PlayerBoard playerBoard;
  static final int PITS_MAX_NUMBER = 6;

  Player(String name, PlayerBoard playerBoard) {
    this.name = name;
    this.score = 0;
    this.playerBoard = playerBoard;
  }

  static Player UNKNOWN_PLAYER() {
    return new Player("UNKNOWN", null);
  }

  String getName() {
    return name;
  }

  int getScore() {
    return score;
  }

  int addScore(int scores) {
    this.score += scores;
    return score;
  }

  int addScore() {
    return ++score;
  }

  boolean cantmove() {
    return playerBoard.noPossibleMoves();
  }

  void collectAllStones() {
    playerBoard.stream().forEach(pitEntry -> addScore(pitEntry.getValue().removeStones()));
  }

  void addStoneToPit(int pitNumber) {
    playerBoard.addStone(pitNumber);
  }

  int removeAllStonesFromOppositePitAndGet(int pitNumber) {
    int opposite = (PITS_MAX_NUMBER - pitNumber) + 1;
    return playerBoard.removeAllAndGet(opposite);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Player player = (Player) o;
    return Objects.equals(name, player.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  int removeStonesFromPit(int pitNumber) {
    return playerBoard.removeAllAndGet(pitNumber);

  }

  int getStonesCountForPit(int pit) {
    return playerBoard.numberOfStonesInPit(pit);
  }

  public String getBoardStatus() {
    return playerBoard.boardStatusToString();
  }
}
