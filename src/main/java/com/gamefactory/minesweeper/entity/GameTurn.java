package com.gamefactory.minesweeper.entity;

import lombok.Data;

@Data
public class GameTurn {

    private String gameId;
    private Integer col;
    private Integer row;

}
