package com.gamefactory.minesweeper.service;

import com.gamefactory.minesweeper.entity.Game;

public interface GameService {

    Game createNewGame(Game game);

    Game getGameById(String id);

}
