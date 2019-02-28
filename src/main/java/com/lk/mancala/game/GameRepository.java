package com.lk.mancala.game;

import java.util.Optional;
import java.util.UUID;

public interface GameRepository {

  Optional<Game> findGameById(UUID gameId);

  Game saveGame(Game game);

}
