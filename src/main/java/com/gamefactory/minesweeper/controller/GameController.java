package com.gamefactory.minesweeper.controller;

import com.gamefactory.minesweeper.dto.ErrorMessage;
import com.gamefactory.minesweeper.dto.GameDto;
import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.OK)
    public GameDto createNewGame(@RequestBody GameDto gameDto) {
        Game createdGame = gameService.createNewGame(gameDto.getGame());
        return GameDto.of(createdGame);
    }

    @ExceptionHandler(Exception.class)
    public ErrorMessage handleError(Exception e) {
        return new ErrorMessage(e.getMessage());
    }

}
