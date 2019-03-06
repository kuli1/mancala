package com.lk.mancala.game;

import com.lk.mancala.game.events.GameEnded;
import com.lk.mancala.game.events.GameStarted;
import com.lk.mancala.game.events.PointsScored;
import com.lk.mancala.game.events.TurnDone;

public interface GameEvents {

  void emit(GameStarted gameStarted);
  void emit(TurnDone turnDone);
  void emit(GameEnded gameEnded);
  void emit(PointsScored pointsScored);

}
