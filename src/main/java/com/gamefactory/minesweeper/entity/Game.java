package com.gamefactory.minesweeper.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@Getter
@Setter
@ToString
public class Game {

    private String gameId;

    private Integer width;

    private Integer height;

    private Integer minesCount;

    private Boolean completed;

    private Field field;

    public Game() {
        gameId = UUID.randomUUID().toString();
        completed = false;
        field = new Field();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameId.equals(game.gameId);
    }

    @Override
    public int hashCode() {
        return gameId.hashCode();
    }

}
