package com.gamefactory.minesweeper.service.impl;

import com.gamefactory.minesweeper.entity.Cell;
import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.entity.GameTurn;
import com.gamefactory.minesweeper.repository.GameRepository;
import com.gamefactory.minesweeper.service.GameService;
import com.gamefactory.minesweeper.utils.GameConstants;
import com.gamefactory.minesweeper.utils.GameUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createNewGame(Game game) {
        GameUtils.validateNewGameParameters(game);
        GameUtils.generateGameField(game);
        return gameRepository.saveGame(game).orElseThrow(() -> new RuntimeException("Game was not created"));
    }

    @Override
    public Game getGameById(String id) {
        return gameRepository.getGameById(id).orElseThrow(() -> new RuntimeException("There is not game with id=" + id));
    }

    @Override
    public Game makeTurn(GameTurn gameTurn) {
        //TODO: delete
        System.out.println("turn: {" + gameTurn.getCol() + ", " + gameTurn.getRow() + "}");
        GameUtils.validateGameTurnMandatoryParameters(gameTurn);
        Game game = getGameById(gameTurn.getGameId());
        GameUtils.validateGameTurnParameters(gameTurn, game);
        if (game.getCompleted()) {
            throw new RuntimeException("This game is already over: " + game);
        }

        Optional<Cell> mineOpt = GameUtils.getMineForCell(gameTurn, game);
        if (mineOpt.isPresent()) {
            GameUtils.fillEntireFieldWithMineValue(game, GameConstants.LOSE_MINE_CHAR);
            game.setCompleted(true);
            gameRepository.deleteGameById(game.getGameId());
            return game;
        }

        Cell cell = new Cell(gameTurn.getCol(), gameTurn.getRow());
        GameUtils.fillCellValue(game, cell, new ArrayList<>());


        if (game.getField().getMines().size() == game.getField().getCountNotOpenedFields()) {
            GameUtils.fillEntireFieldWithMineValue(game, GameConstants.WIN_MINE_CHAR);
            game.setCompleted(true);
            gameRepository.deleteGameById(game.getGameId());
            return game;
        }


        return game;
    }

}
