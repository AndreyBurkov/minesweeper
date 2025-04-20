package com.gamefactory.minesweeper.validator;

import com.gamefactory.minesweeper.config.GameProperties;
import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.entity.GameTurn;
import com.gamefactory.minesweeper.utils.GameConstants;
import org.springframework.stereotype.Component;

@Component
public class GameValidator {

    private final GameProperties gameProperties;

    public GameValidator(GameProperties gameProperties) {
        this.gameProperties = gameProperties;
    }

    public void validateNewGameParameters(Game game) {
        Integer height = game.getHeight();
        Integer width = game.getWidth();
        Integer minesCount = game.getMinesCount();
        if (height == null || width == null || minesCount == null) {
            throw new RuntimeException("Parameters width, height, mines_count are mandatory");
        }

        if (height > gameProperties.getMaxFieldHeight() || height < GameConstants.MIN_FIELD_HEIGHT) {
            throw new RuntimeException("Field height must be in [" + GameConstants.MIN_FIELD_HEIGHT + ", " + gameProperties.getMaxFieldHeight() + "]");
        }
        if (width > gameProperties.getMaxFieldWidth() || width < GameConstants.MIN_FIELD_WIDTH) {
            throw new RuntimeException("Field width must be in [" + GameConstants.MIN_FIELD_WIDTH + ", " + gameProperties.getMaxFieldWidth() + "]");
        }
        if (minesCount > height * width - 1) {
            throw new RuntimeException("mines_count cannot be more than height * width - 1");
        }
    }

    public void validateGameTurnMandatoryParameters(GameTurn gameTurn) {
        if (gameTurn.getGameId() == null || gameTurn.getGameId().isEmpty()
                || gameTurn.getCol() == null || gameTurn.getRow() == null) {
            throw new RuntimeException("game_id, col, row cannot be empty");
        }
    }

    public void validateGameTurnParameters(GameTurn gameTurn, Game game) {
        Integer col = gameTurn.getCol();
        Integer row = gameTurn.getRow();
        if (col < 0 || col >= game.getWidth()) {
            throw new RuntimeException("col must be in bounds: [0;" + game.getWidth() + ")");
        }
        if (row < 0 || row >= game.getHeight()) {
            throw new RuntimeException("row must be in bounds: [0;" + game.getHeight() + ")");
        }
    }

}
