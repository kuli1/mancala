package com.lk.mancala.game;

class TurnCommand {

  private String playerName;
  private int pitNumber;

  public TurnCommand(String playerName, int pitNumber) {
    this.playerName = playerName;
    this.pitNumber = pitNumber;
  }

  String getPlayerName() {
    return playerName;
  }

  int getPitNumber() {
    return pitNumber;
  }
}
