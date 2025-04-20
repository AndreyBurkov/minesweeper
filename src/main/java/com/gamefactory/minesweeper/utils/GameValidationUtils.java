package com.gamefactory.minesweeper.utils;

import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.entity.GameTurn;

public class GameValidationUtils {

    public static void validateNewGameParameters(Game game) {
        Integer height = game.getHeight();
        Integer width = game.getWidth();
        Integer minesCount = game.getMinesCount();
        if (height == null || width == null || minesCount == null) {
            throw new RuntimeException("Parameters width, height, mines_count are mandatory");
        }
        if (height > GameConstants.MAX_FIELD_HEIGHT || height < GameConstants.MIN_FIELD_HEIGHT) {
            throw new RuntimeException("Field height must be in [" + GameConstants.MIN_FIELD_HEIGHT + ", " + GameConstants.MAX_FIELD_HEIGHT + "]");
        }
        if (width > GameConstants.MAX_FIELD_WIDTH || width < GameConstants.MIN_FIELD_WIDTH) {
            throw new RuntimeException("Field width must be in [" + GameConstants.MIN_FIELD_WIDTH + ", " + GameConstants.MAX_FIELD_WIDTH + "]");
        }
        if (minesCount > height * width - 1) {
            throw new RuntimeException("mines_count cannot be more than height * width - 1");
        }
    }

    public static void validateGameTurnMandatoryParameters(GameTurn gameTurn) {
        if (gameTurn.getGameId() == null || gameTurn.getGameId().isEmpty()
                || gameTurn.getCol() == null || gameTurn.getRow() == null) {
            throw new RuntimeException("game_id, col, row cannot be empty");
        }
    }

    public static void validateGameTurnParameters(GameTurn gameTurn, Game game) {
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
