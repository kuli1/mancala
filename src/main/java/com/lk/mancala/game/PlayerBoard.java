package com.lk.mancala.game;

import static java.util.Comparator.*;
import static java.util.Map.of;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PlayerBoard {

  Map<Integer, Pit> board;

  PlayerBoard(Map<Integer, Pit> board) {
    this.board = board;
  }

  static PlayerBoard standardBoard() {
    return new PlayerBoard(of(1, Pit.defaultPit(), 2, Pit.defaultPit(), 3, Pit.defaultPit(), 4,
        Pit.defaultPit(), 5, Pit.defaultPit(), 6, Pit.defaultPit()));
  }

  void addStone(int pitNmber) {
    board.get(pitNmber).addNewStone();
  }

  int numberOfStonesInPit(int pitNumber) {
    return board.get(pitNumber).getStonesCounter();
  }

  Stream<Map.Entry<Integer, Pit>> stream() {
    return board.entrySet().stream();
  }

  boolean noPossibleMoves() {
    return board.entrySet().stream().mapToInt(value -> value.getValue().getStonesCounter()).sum()
        == 0;
  }

  int getAllStonesFromOpposite(int pitNumber) {
    int opposite = (maxNumberOfPits() - pitNumber) + 1;
    return removeAllAndGet(opposite);
  }

  int removeAllAndGet(int pitNumber) {
    return board.get(pitNumber).removeStones();
  }

  String boardStatusToString() {
    return board
        .entrySet()
        .stream()
        .sorted(comparing(Entry::getKey))
        .map(pitEntry -> String.format("Pit %s stones = %s ", pitEntry.getKey(),
            pitEntry.getValue().getStonesCounter()))
        .collect(
            Collectors.joining());
  }

  int maxNumberOfPits(){
    return board.size();
  }
}
