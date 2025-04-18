package com.gamefactory.minesweeper.utils;

import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.entity.Mine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameUtils {

    public static void validateNewGameParameters(Game game) {
        Integer height = game.getHeight();
        Integer width = game.getWidth();
        Integer minesCount = game.getMinesCount();
        if (height == null || width == null || minesCount == null) {
            throw new RuntimeException("Parameters width, height, mines_count are mandatory");
        }
        if (height > GameConstants.MAX_FIELD_HEIGHT) {
            throw new RuntimeException("Field height cannon br more than " + GameConstants.MAX_FIELD_HEIGHT);
        }
        if (width > GameConstants.MAX_FIELD_WIDTH) {
            throw new RuntimeException("Field width cannon br more than " + GameConstants.MAX_FIELD_WIDTH);
        }
        if (minesCount > height * width - 1) {
            throw new RuntimeException("mines_count cannot be more than height * width - 1");
        }
    }

    public static void generateField(Game game) {
        List<List<Character>> cells = game.getField().getCells();
        for (int y = 0; y < game.getHeight(); y++) {
            List<Character> row = new ArrayList<>();
            for (int x = 0; x < game.getWidth(); x++) {
                row.add(' ');
            }
            cells.add(row);
        }
        for (int i = 0; i < game.getMinesCount(); i++) {
            int x = ThreadLocalRandom.current().nextInt(0, game.getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, game.getHeight());
            Mine mine = new Mine(x, y);
            game.getField().getMines().add(mine);
        }
    }

}
