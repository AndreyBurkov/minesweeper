package com.gamefactory.minesweeper.repository.impl;

import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.repository.GameRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryGameRepository implements GameRepository {

    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();

    @Override
    public Optional<Game> saveGame(Game game) {
        games.put(game.getGameId(), game);
        return getGameById(game.getGameId());
    }

    @Override
    public Optional<Game> getGameById(String id) {
        return Optional.ofNullable(games.get(id));
    }

}
