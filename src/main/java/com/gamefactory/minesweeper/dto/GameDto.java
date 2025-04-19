package com.gamefactory.minesweeper.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.gamefactory.minesweeper.entity.Game;

import java.util.List;

@JsonPropertyOrder({"game_id", "width", "height", "mines_count", "completed", "field"})
public class GameDto {

    @JsonIgnore
    private final Game game;

    public GameDto() {
        this.game = new Game();
    }

    private GameDto(Game game) {
        this.game = game;
    }

    public static GameDto of(Game game) {
        return new GameDto(game);
    }

    public Game getGame() {
        return game;
    }

    @JsonGetter("game_id")
    public String getGameId() {
        return game.getGameId();
    }

    public Integer getWidth() {
        return game.getWidth();
    }

    public void setWidth(Integer width) {
        game.setWidth(width);
    }

    public Integer getHeight() {
        return game.getHeight();
    }

    public void setHeight(Integer height) {
        game.setHeight(height);
    }

    @JsonGetter("mines_count")
    public Integer getMinesCount() {
        return game.getMinesCount();
    }

    @JsonSetter("mines_count")
    public void setMinesCount(Integer minesCount) {
        game.setMinesCount(minesCount);
    }

    public Boolean getCompleted() {
        return game.getCompleted();
    }

    public List<List<Character>> getField() {
        return game.getField().getCells();
    }

}
