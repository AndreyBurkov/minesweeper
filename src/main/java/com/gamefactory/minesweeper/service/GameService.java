package com.gamefactory.minesweeper.service;

import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.entity.GameTurn;

public interface GameService {

    Game createNewGame(Game game);

    Game getGameById(String id);

    Game makeTurn(GameTurn gameTurn);

}
