package com.gamefactory.minesweeper.entity;

import com.gamefactory.minesweeper.utils.GameConstants;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final List<List<Character>> cells;

    private final List<Cell> mines;

    public Field() {
        cells = new ArrayList<>();
        mines = new ArrayList<>();
    }

    public List<List<Character>> getCells() {
        return cells;
    }

    public List<Cell> getMines() {
        return mines;
    }

    public void setCellCharValue(int x, int y, char value) {
        cells.get(y).set(x, value);
    }

    public Character getCellCharValue(int x, int y) {
        return cells.get(y).get(x);
    }

    public int getCountNotOpenedFields() {
        return (int) cells.stream()
                .mapToLong(list -> list.stream().filter(character -> character == GameConstants.INITIAL_MINE_CHAR).count())
                .sum();
    }

}
