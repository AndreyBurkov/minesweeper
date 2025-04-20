package com.gamefactory.minesweeper.entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class Game {

    private String gameId;

    private Integer width;

    private Integer height;

    private Integer minesCount;

    private Boolean completed;

    private Field field;

    private LocalDateTime createdAt;

    public Game() {
        gameId = UUID.randomUUID().toString();
        completed = false;
        field = new Field();
        createdAt = LocalDateTime.now();
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getMinesCount() {
        return minesCount;
    }

    public void setMinesCount(Integer minesCount) {
        this.minesCount = minesCount;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", minesCount=" + minesCount +
                ", completed=" + completed +
                ", field=" + field +
                ", createdAt=" + createdAt +
                '}';
    }
}
