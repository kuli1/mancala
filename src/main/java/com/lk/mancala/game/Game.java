package com.lk.mancala.game;

import com.lk.mancala.game.events.GameEndEvent;
import com.lk.mancala.game.events.ScoredEvent;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class Game {

  static final int PITS_MAX_NUMBER = 6;
  private Map<String, Player> players;
  private UUID gameId;
  private Player currentPlayerV2;
  private Player nextPlayerV2;
  private boolean endOfGame;
  private GameEvents gameEvents;

  public Game(Map<String, Player> players,
      Player startingPlayer, GameEvents gameEvents) {
    this.players = players;
    currentPlayerV2 = startingPlayer;
    nextPlayerV2 = startingPlayer;
    gameId = UUID.randomUUID();
    this.gameEvents = gameEvents;
  }

  Map<String, Player> doTurn(TurnCommand turnCommand) {
    isEndOfGame();
    Player player = players.getOrDefault(turnCommand.getPlayerName(), Player.UNKNOWN_PLAYER());

    Player nextPlayer = players.entrySet()
        .stream().filter(entry -> !entry.getKey().equalsIgnoreCase(turnCommand.getPlayerName()))
        .findFirst().map(Entry::getValue)
        .orElseThrow(() -> new RuntimeException("No Available Player !"));

    currentPlayerV2 = player;
    checkPlayerMove();
    nextPlayerV2 = nextPlayer;
    if (checkIfEndOfGame()) {
      gameEvents.emit(new GameEndEvent(gameId));
      return players;
    }
    distributeStones(currentPlayerV2.removeStonesFromPit(turnCommand.getPitNumber()),
        turnCommand.getPitNumber(), true);
    return players;
  }

  private void isEndOfGame() {
    if (endOfGame) {
      gameEvents.emit(new GameEndEvent(gameId));
      throw new RuntimeException("This Game is already over !");
    }
  }

  public int getScores(String playerName) {
    return players.getOrDefault(playerName, Player.UNKNOWN_PLAYER())
        .getScore();
  }

  public String whichPlayerTurn() {
    return currentPlayerV2.getName();
  }

  public int playerStonesInPit(String playerName, int pit) {
    return players.getOrDefault(playerName, Player.UNKNOWN_PLAYER())
        .getStonesCountForPit(pit);
  }

  public UUID gemeId() {
    return gameId;
  }

  private boolean checkIfEndOfGame() {
    if (currentPlayerV2.cantmove()) {
      nextPlayerV2.collectAllStones();
      return true;
    }
    return false;
  }

  private void checkPlayerMove() {
    if (!nextPlayerV2.equals(currentPlayerV2)) {
      throw new RuntimeException("This player already palyed !");
    }
  }

  private void distributeStones(int numberOfStones, int pitNumber, boolean myBoard) {

    pitNumber = pitNumber + 1;

    if (pitNumber > PITS_MAX_NUMBER && numberOfStones > 0) {
      if (myBoard) {
        currentPlayerV2.addScore();
        gameEvents.emit(new ScoredEvent(currentPlayerV2.getName(), 1, gameId));
        if (numberOfStones == 1) {
          nextPlayerV2 = currentPlayerV2;
          return;
        }
        distributeStones(numberOfStones - 1, 0, false);
        return;
      }
      distributeStones(numberOfStones, 0, true);
      return;
    }
    if (numberOfStones > 0) {
      //last stone
      addStoneToThePit(pitNumber, myBoard);
      tryToTakeOver(numberOfStones, pitNumber, myBoard);
      distributeStones(numberOfStones - 1, pitNumber, myBoard);
      return;
    }

    if (numberOfStones == 0) {
      return;
    }
  }

  private void addStoneToThePit(int pitNumber, boolean myBoard) {
    if (myBoard) {
      currentPlayerV2.addStoneToPit(pitNumber);
    } else {
      nextPlayerV2.addStoneToPit(pitNumber);
    }
  }

  private void tryToTakeOver(int numberOfStones, int pitNumber, boolean myBoard) {
    if (takeOverPossible(numberOfStones, myBoard)) {
      int stonesToRemove = nextPlayerV2.removeAllStonesFromOppositePitAndGet(pitNumber);
      currentPlayerV2.addScore(stonesToRemove);
      gameEvents.emit(new ScoredEvent(currentPlayerV2.getName(), stonesToRemove, gameId));
    }
  }

  private boolean takeOverPossible(int numberOfStones, boolean myBoard) {
    return numberOfStones == 1 && myBoard;
  }

}
