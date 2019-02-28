package com.lk.mancala.game.dto;

import java.util.Map;

public class BoardStatusDTO {
  private Map<String, BoardDTO> boardStatus;

  public BoardStatusDTO(Map<String, BoardDTO> boardStatus) {
    this.boardStatus = boardStatus;
  }

  public Map<String, BoardDTO> getBoardStatus() {
    return boardStatus;
  }
}
