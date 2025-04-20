package com.gamefactory.minesweeper.repository.impl;

import com.gamefactory.minesweeper.config.GameProperties;
import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.repository.GameRepository;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryGameRepository implements GameRepository {

    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    private final GameProperties gameProperties;

    public InMemoryGameRepository(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
    }

    @Override
    public Optional<Game> saveGame(Game game) {
        games.put(game.getGameId(), game);
        return getGameById(game.getGameId());
    }

    @Override
    public Optional<Game> getGameById(String id) {
        return Optional.ofNullable(games.get(id));
    }

    @Override
    public void deleteGameById(String id) {
        games.remove(id);
    }

    @Override
    public int getGamesCount() {
        return games.size();
    }

    @Override
    public List<String> getGamesGameIdsToRemove() {
        return games.entrySet().stream()
                .filter(entry -> Duration.between(entry.getValue().getCreatedAt(), LocalDateTime.now()).toMinutes()
                        > gameProperties.getMaxGameDurationMinutes() || entry.getValue().getCompleted())
                .map(Map.Entry::getKey).toList();
    }

}
