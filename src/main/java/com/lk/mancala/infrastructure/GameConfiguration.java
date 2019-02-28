package com.lk.mancala.infrastructure;

import com.lk.mancala.game.GameEvents;
import com.lk.mancala.game.GameFactory;
import com.lk.mancala.game.GameRepository;
import com.lk.mancala.game.GameService;
import com.lk.mancala.game.readmodel.GameStatsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameConfiguration {

  @Bean
  public GameRepository gameRepository() {
    return new GameInMemoryRepository();
  }

  @Bean
  public GameFactory gameFactory(GameEvents gameEvents) {
    return new GameFactory(gameEvents);
  }

  @Bean
  public GameService gameService(GameFactory gameFactory, GameRepository gameRepository,
      GameStatsRepository gameStatsRepository) {
    return new GameService(gameFactory, gameRepository, gameStatsRepository);
  }

  @Bean
  public GameStatsRepository gameStatsRepository() {
    return new InMemoryGameStatsRepository();
  }

  @Bean
  public GameEvents gameEvents(GameStatsRepository gameStatsRepository) {
    return new InMemoryGameEventPropagator(gameStatsRepository);
  }

}
