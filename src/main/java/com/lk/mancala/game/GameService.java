package com.lk.mancala.game;

import com.lk.mancala.game.readmodel.GameStatsData;
import com.lk.mancala.game.readmodel.GameStatsRepository;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class GameService {

  private final GameFactory gameFactory;
  private final GameRepository gameRepository;
  private final GameStatsRepository gameStatsRepository;

  public GameService(GameFactory gameFactory, GameRepository gameRepository,
      GameStatsRepository gameStatsRepository) {
    this.gameFactory = gameFactory;
    this.gameRepository = gameRepository;
    this.gameStatsRepository = gameStatsRepository;
  }

  public UUID startNewGame(String player1, String player2) {
    Game game = gameFactory
        .startGameFor(player1, player2);
    return gameRepository
        .saveGame(game)
        .gemeId();
  }

  public String gameStatus(UUID gameId) {
    GameStatsData gameStatsData = gameStatsRepository.readGameStats(gameId);
    return gameStatsData.getGameSatus().name();
  }

  public String playerMove(UUID gameId, String playerName, int pit) {
    Game game = gameRepository.findGameById(gameId)
        .orElseThrow(() -> new RuntimeException("Can't find game with given ID !"));
    TurnCommand turnCommand = new TurnCommand(playerName, pit);
    Map<String, Player> gameStatus = game.doTurn(turnCommand);

    return gameStatus
        .entrySet()
        .stream()
        .map(stringPlayerEntry -> "Player : " + stringPlayerEntry.getKey() + " board :"
            + stringPlayerEntry.getValue().getBoardStatus()).collect(
            Collectors.joining());
  }

}
