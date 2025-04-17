package com.gamefactory.minesweeper.service.impl;

import com.gamefactory.minesweeper.model.Game;
import com.gamefactory.minesweeper.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public Game createNewGame(Game game) {
        return game;
    }

}
