package com.gamefactory.minesweeper.controller;

import com.gamefactory.minesweeper.model.Game;
import com.gamefactory.minesweeper.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> createNewGame(@RequestBody Game game) {
        Game createdGame = gameService.createNewGame(game);
        return ResponseEntity.ok(createdGame);
    }

}
