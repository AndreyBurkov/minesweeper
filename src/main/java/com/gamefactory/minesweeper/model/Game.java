package com.gamefactory.minesweeper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Game {

    @JsonProperty(value = "game_id", index = 1)
    private String gameId;

    @JsonProperty(value = "width", index = 2)
    private Integer width;

    @JsonProperty(value = "height", index = 3)
    private Integer height;

    @JsonProperty(value = "mines_count", index = 4)
    private Integer minesCount;

    @JsonProperty(value = "completed", index = 5)
    private Boolean completed;

    @JsonProperty(value = "field", index = 6)
    private List<List<Character>> field;

    public Game() {
        gameId = UUID.randomUUID().toString();
        completed = false;
    }

}
