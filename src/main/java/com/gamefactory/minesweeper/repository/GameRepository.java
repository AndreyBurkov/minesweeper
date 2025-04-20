package com.gamefactory.minesweeper.repository;

import com.gamefactory.minesweeper.entity.Game;

import java.util.List;
import java.util.Optional;

public interface GameRepository {

    Optional<Game> saveGame(Game game);

    Optional<Game> getGameById(String id);

    void deleteGameById(String id);

    int getGamesCount();

    List<String> getGamesGameIdsToRemove();

}
