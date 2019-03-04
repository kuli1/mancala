package com.lk.mancala.game;

import com.lk.mancala.game.events.GameEndEvent;
import com.lk.mancala.game.events.ScoredEvent;
import com.lk.mancala.game.readmodel.GameException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class Game {

  private Map<String, Player> players;
  private UUID gameId;
  private Player currentPlayer;
  private Player nextPlayer;
  private boolean endOfGame;
  private final int maxPitNumber;
  private GameEvents gameEvents;

  public Game(Map<String, Player> players,
      Player startingPlayer, int maxPitNumber, GameEvents gameEvents) {
    this.players = players;
    currentPlayer = startingPlayer;
    nextPlayer = startingPlayer;
    this.maxPitNumber = maxPitNumber;
    gameId = UUID.randomUUID();
    this.gameEvents = gameEvents;
  }

  Map<String, Player> doTurn(TurnCommand turnCommand) {
    isGameActive();
    isValidPlayer(turnCommand.getPlayerName());
    setPlayer(turnCommand);
    checkPlayerMove();
    setNexPlayer(turnCommand);
    if (isMovePossible()) {
      gameEvents.emit(new GameEndEvent(gameId));
      return players;
    }
    finishTurn(turnCommand);
    return players;
  }

  int getScores(String playerName) {
    return players.getOrDefault(playerName, Player.UNKNOWN_PLAYER())
        .getScore();
  }

  String whichPlayerTurn() {
    return currentPlayer.getName();
  }

  int playerStonesInPit(String playerName, int pit) {
    return players.getOrDefault(playerName, Player.UNKNOWN_PLAYER())
        .getStonesCountForPit(pit);
  }

  public UUID gemeId() {
    return gameId;
  }

  private void finishTurn(TurnCommand turnCommand) {
    distributeStones(currentPlayer.removeStonesFromPit(turnCommand.getPitNumber()),
        turnCommand.getPitNumber(), true);
  }

  private void setPlayer(TurnCommand turnCommand) {
    Player player = players.getOrDefault(turnCommand.getPlayerName(), Player.UNKNOWN_PLAYER());
    this.currentPlayer = player;
  }

  private void setNexPlayer(TurnCommand turnCommand) {
    Player nextPlayer = getNextPlayer(turnCommand);
    this.nextPlayer = nextPlayer;
  }

  private Player getNextPlayer(TurnCommand turnCommand) {
    return players.entrySet()
        .stream().filter(entry -> !entry.getKey().equalsIgnoreCase(turnCommand.getPlayerName()))
        .findFirst().map(Entry::getValue)
        .orElseThrow(GameException::noAvailablePlayer);
  }

  private void isValidPlayer(String playerName) {
    if(!players.containsKey(playerName)){
      throw GameException.invalidPlayerName(playerName);
    }
  }

  private void isGameActive() {
    if (endOfGame) {
      throw GameException.gameOver();
    }
  }

  private boolean isMovePossible() {
    if (currentPlayer.cantmove()) {
      endOfGame = true;
      nextPlayer.collectAllStones();
      return true;
    }
    return false;
  }

  private void checkPlayerMove() {
    if (!nextPlayer.equals(currentPlayer)) {
      throw GameException.playerAlreadyPlayed(currentPlayer.getName());
    }
  }

  private void distributeStones(int numberOfStones, int pitNumber, boolean myBoard) {

    pitNumber = pitNumber + 1;

    if (numberOfStones == 0) {
      return;
    }

    if (pitNumber > maxPitNumber && numberOfStones > 0) {
      if (myBoard) {
        currentPlayer.addScore();
        gameEvents.emit(new ScoredEvent(currentPlayer.getName(), 1, gameId));
        if (numberOfStones == 1) {
          nextPlayer = currentPlayer;
          return;
        }
        distributeStones(numberOfStones - 1, 0, false);
        return;
      }
      distributeStones(numberOfStones, 0, true);
      return;
    }
    if (numberOfStones > 0) {
      addStoneToThePit(pitNumber, myBoard);
      tryToTakeOver(numberOfStones, pitNumber, myBoard);
      distributeStones(numberOfStones - 1, pitNumber, myBoard);
      return;
    }

  }

  private void addStoneToThePit(int pitNumber, boolean myBoard) {
    if (myBoard) {
      currentPlayer.addStoneToPit(pitNumber);
    } else {
      nextPlayer.addStoneToPit(pitNumber);
    }
  }

  private void tryToTakeOver(int numberOfStones, int pitNumber, boolean myBoard) {
    if (takeOverPossible(numberOfStones, myBoard)) {
      int stonesToRemove = nextPlayer.removeAllStonesFromOppositePitAndGet(pitNumber);
      currentPlayer.addScore(stonesToRemove);
      gameEvents.emit(new ScoredEvent(currentPlayer.getName(), stonesToRemove, gameId));
    }
  }

  private boolean takeOverPossible(int numberOfStones, boolean myBoard) {
    return numberOfStones == 1 && myBoard;
  }

}
