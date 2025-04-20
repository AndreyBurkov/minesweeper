package com.gamefactory.minesweeper.service.impl;

import com.gamefactory.minesweeper.config.GameProperties;
import com.gamefactory.minesweeper.entity.Cell;
import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.entity.GameTurn;
import com.gamefactory.minesweeper.repository.GameRepository;
import com.gamefactory.minesweeper.service.GameService;
import com.gamefactory.minesweeper.utils.GameConstants;
import com.gamefactory.minesweeper.utils.GameUtils;
import com.gamefactory.minesweeper.utils.GameValidationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final GameProperties gameProperties;
    private final Lock lock = new ReentrantLock();

    public GameServiceImpl(GameRepository gameRepository, GameProperties gameProperties) {
        this.gameRepository = gameRepository;
        this.gameProperties = gameProperties;
    }

    @Override
    public Game createNewGame(Game game) {
        GameValidationUtils.validateNewGameParameters(game);
        GameUtils.generateGameField(game);

        lock.lock();
        try {
            if (gameRepository.getGamesCount() > gameProperties.getMaxGamesCount()) {
                List<String> gameIdsToRemove = gameRepository.getGamesGameIdsToRemove();
                if (gameIdsToRemove.size() > 0) {
                    gameIdsToRemove.forEach(gameRepository::deleteGameById);
                } else {
                    throw new RuntimeException("Games are limited by count " + gameProperties.getMaxGamesCount()
                            + " and by " + gameProperties.getMaxGameDurationMinutes() + " minutes. Please try later");
                }
            }
            return gameRepository.saveGame(game).orElseThrow(() -> new RuntimeException("Game was not created"));
        } finally {
            lock.unlock();
        }
    }

    @Override
    public Game getGameById(String id) {
        return gameRepository.getGameById(id).orElseThrow(() -> new RuntimeException("There is not game with id=" + id));
    }

    @Override
    public Game makeTurn(GameTurn gameTurn) {
        GameValidationUtils.validateGameTurnMandatoryParameters(gameTurn);
        Game game = getGameById(gameTurn.getGameId());
        GameValidationUtils.validateGameTurnParameters(gameTurn, game);
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
