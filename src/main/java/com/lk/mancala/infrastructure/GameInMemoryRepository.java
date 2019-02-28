package com.lk.mancala.infrastructure;

import com.lk.mancala.game.Game;
import com.lk.mancala.game.GameRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

class GameInMemoryRepository implements GameRepository {

  private Map<UUID, Game> games = new HashMap<>();

  public Optional<Game> findGameById(UUID gameId) {
    if (games.containsKey(gameId)) {
      return Optional.of(games.get(gameId));
    }
    return Optional.empty();
  }

  public Game saveGame(Game game) {
    games.putIfAbsent(game.gemeId(), game);
    return game;
  }

}
