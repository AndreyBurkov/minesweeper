package com.gamefactory.minesweeper.config;

import com.gamefactory.minesweeper.utils.GameConstants;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "minesweeper.game")
public class GameProperties {

    private int maxGamesCount;
    private int maxGameDurationMinutes;
    private int maxFieldHeight;
    private int maxFieldWidth;

    @PostConstruct
    private void validateAndUpdateProperties() {
        if (maxGamesCount < 1) {
            maxGamesCount = GameConstants.DEFAULT_MAX_GAMES_COUNT;
        }
        if (maxGameDurationMinutes < 1) {
            maxGameDurationMinutes = GameConstants.DEFAULT_MAX_GAME_DURATION_MINUTES;
        }
        if (maxFieldHeight < 2 || maxFieldHeight > 30) {
            maxFieldHeight = GameConstants.DEFAULT_MAX_GAME_HEIGHT;
        }
        if (maxFieldWidth < 2 || maxFieldWidth > 30) {
            maxFieldWidth = GameConstants.DEFAULT_MAX_GAME_WIDTH;
        }
    }

}
