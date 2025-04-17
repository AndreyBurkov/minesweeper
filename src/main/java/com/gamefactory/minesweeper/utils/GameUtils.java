package com.gamefactory.minesweeper.utils;

import com.gamefactory.minesweeper.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GameUtils {

    public static List<List<Character>> generateField(Game game) {
        List<List<Character>> field = new ArrayList<>();
        for (int y = 0; y < game.getHeight(); y++) {
//            for (int x = 0; x < game.)
        }
        for (int i = 0; i <= game.getMinesCount(); i++) {
            int x = ThreadLocalRandom.current().nextInt(0, game.getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, game.getHeight());
        }
        return null;
    }

    //TODO
    public void validateNewGameParameters(Game game) {
    }

}
