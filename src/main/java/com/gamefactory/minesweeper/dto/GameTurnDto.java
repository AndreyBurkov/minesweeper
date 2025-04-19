package com.gamefactory.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.gamefactory.minesweeper.entity.GameTurn;

public class GameTurnDto {

    @JsonIgnore
    private final GameTurn gameTurn;

    public GameTurnDto() {
        gameTurn = new GameTurn();
    }

    public GameTurn getGameTurn() {
        return gameTurn;
    }

    @JsonSetter("game_id")
    public void setGameId(String gameId) {
        gameTurn.setGameId(gameId);
    }

    public void setCol(Integer col) {
        gameTurn.setCol(col);
    }

    public void setRow(Integer row) {
        gameTurn.setRow(row);
    }

}
