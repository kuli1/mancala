package com.lk.mancala.game.dto;

import java.util.Map;

public class BoardDTO {
  private Map<Integer, Integer> board;

  public BoardDTO(Map<Integer, Integer> board) {
    this.board = board;
  }

  public Map<Integer, Integer> getBoard() {
    return board;
  }
}
