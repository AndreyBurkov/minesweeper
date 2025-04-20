package com.gamefactory.minesweeper.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minesweeper.game")
public class GameProperties {

    private int maxGamesCount;
    private int maxGameDurationMinutes;
    private int maxFieldHeight;
    private int maxFieldWidth;

}
