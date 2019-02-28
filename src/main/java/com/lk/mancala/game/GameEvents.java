package com.lk.mancala.game;

import com.lk.mancala.game.events.GameEndEvent;
import com.lk.mancala.game.events.GameStartedEvent;
import com.lk.mancala.game.events.ScoredEvent;
import com.lk.mancala.game.events.TurnDoneEvent;

public interface GameEvents {

  void emit(GameStartedEvent gameStartedEvent);
  void emit(TurnDoneEvent turnDoneEvent);
  void emit(GameEndEvent gameEndEvent);
  void emit(ScoredEvent scoredEvent);

}
