package com.lk.mancala.game;

import static java.util.Comparator.*;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class PlayerBoard {

  Map<Integer, Pit> board;

  PlayerBoard(Map<Integer, Pit> board) {
    this.board = board;
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
}
