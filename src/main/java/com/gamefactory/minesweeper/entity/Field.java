package com.gamefactory.minesweeper.entity;

import java.util.ArrayList;
import java.util.List;

public class Field {

    private final List<List<Character>> cells;

    private final List<Mine> mines;

    public Field() {
        cells = new ArrayList<>();
        mines = new ArrayList<>();
    }

    public List<List<Character>> getCells() {
        return cells;
    }

    public List<Mine> getMines() {
        return mines;
    }

}
