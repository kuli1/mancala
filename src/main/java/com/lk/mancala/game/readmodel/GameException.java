package com.lk.mancala.game.readmodel;

public class GameException extends RuntimeException {

  private GameException(String message){
    super(message);
  }

  public static GameException invalidPlayerName(String player){
    return new GameException(String.format("Player : %s is not valid player name in this game !", player));
  }

  public static GameException playerAlreadyPlayed(String playerName) {
    return new GameException(String.format("Player : %s already played !", playerName));
  }
}
