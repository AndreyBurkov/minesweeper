package com.gamefactory.minesweeper.service.impl;

import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.repository.GameRepository;
import com.gamefactory.minesweeper.service.GameService;
import com.gamefactory.minesweeper.utils.GameUtils;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createNewGame(Game game) {
        GameUtils.validateNewGameParameters(game);
        GameUtils.generateField(game);
        return gameRepository.saveGame(game).orElseThrow(() -> new RuntimeException("Game was not created"));
    }

    @Override
    public Game getGameById(String id) {
        return gameRepository.getGameById(id).orElseThrow(() -> new RuntimeException("There is not game with id=" + id));
    }

}
