package com.lk.mancala.game;

class Pit {

  private int stonesCounter;

  Pit(int stonesCounter) {
    this.stonesCounter = stonesCounter;
  }

  int getStonesCounter() {
    return stonesCounter;
  }

  void addNewStone() {
    stonesCounter++;
  }

  void addStones(int numberOfStones) {
    stonesCounter = stonesCounter + numberOfStones;
  }

  void removeStrone() {
    stonesCounter--;
  }

  static Pit homePit() {
    return new Pit(0);
  }

  public int removeStones() {
    int currentVal = stonesCounter;
    stonesCounter = 0;
    return currentVal;
  }
}
