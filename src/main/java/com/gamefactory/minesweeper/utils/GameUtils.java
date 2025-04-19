package com.gamefactory.minesweeper.utils;

import com.gamefactory.minesweeper.entity.Cell;
import com.gamefactory.minesweeper.entity.Field;
import com.gamefactory.minesweeper.entity.Game;
import com.gamefactory.minesweeper.entity.GameTurn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class GameUtils {

    public static void validateNewGameParameters(Game game) {
        Integer height = game.getHeight();
        Integer width = game.getWidth();
        Integer minesCount = game.getMinesCount();
        if (height == null || width == null || minesCount == null) {
            throw new RuntimeException("Parameters width, height, mines_count are mandatory");
        }
        if (height > GameConstants.MAX_FIELD_HEIGHT || height < GameConstants.MIN_FIELD_HEIGHT) {
            throw new RuntimeException("Field height must be in [" + GameConstants.MIN_FIELD_HEIGHT + ", " + GameConstants.MAX_FIELD_HEIGHT + "]");
        }
        if (width > GameConstants.MAX_FIELD_WIDTH || width < GameConstants.MIN_FIELD_WIDTH) {
            throw new RuntimeException("Field width must be in [" + GameConstants.MIN_FIELD_WIDTH + ", " + GameConstants.MAX_FIELD_WIDTH + "]");
        }
        if (minesCount > height * width - 1) {
            throw new RuntimeException("mines_count cannot be more than height * width - 1");
        }
    }

    public static void validateGameTurnMandatoryParameters(GameTurn gameTurn) {
        if (gameTurn.getGameId() == null || gameTurn.getGameId().isEmpty()
                || gameTurn.getCol() == null || gameTurn.getRow() == null) {
            throw new RuntimeException("game_id, col, row cannot be empty");
        }
    }

    public static void validateGameTurnParameters(GameTurn gameTurn, Game game) {
        Integer col = gameTurn.getCol();
        Integer row = gameTurn.getRow();
        if (col < 0 || col >= game.getWidth()) {
            throw new RuntimeException("col must be in bounds: [0;" + game.getWidth() + ")");
        }
        if (row < 0 || row >= game.getHeight()) {
            throw new RuntimeException("row must be in bounds: [0;" + game.getHeight() + ")");
        }
    }

    public static void generateGameField(Game game) {
        List<List<Character>> cells = game.getField().getCells();
        List<Cell> allMines = new ArrayList<>(game.getHeight() * game.getWidth());
        for (int y = 0; y < game.getHeight(); y++) {
            List<Character> row = new ArrayList<>();
            for (int x = 0; x < game.getWidth(); x++) {
                row.add(GameConstants.INITIAL_MINE_CHAR);
                allMines.add(new Cell(x, y));
            }
            cells.add(row);
        }
        for (int i = 0; i < game.getMinesCount(); i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, allMines.size());
            game.getField().getMines().add(allMines.remove(randomIndex));
        }

        //TODO: delete
        System.out.println("\n\n");
        List<Cell> mines_temp = game.getField().getMines();
        System.out.println("\n" + mines_temp + "\n");
        for (int y = 0; y < game.getHeight(); y++) {
            for (int x = 0; x < game.getWidth(); x++) {
                Cell tempCell = new Cell(x, y);
                if (mines_temp.contains(tempCell)) {
                    System.out.print("X");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }

        System.out.println();
    }

    public static List<Cell> fillCellValue(Game game, Cell cell, List<Cell> cellsNotToProcess) {
        if (cellsNotToProcess.contains(cell)) {
            return cellsNotToProcess;
        }
        cellsNotToProcess.add(cell);

        Field field = game.getField();
        List<Cell> mines = field.getMines();
        Integer x = cell.getX();
        Integer y = cell.getY();
        Integer xMax = game.getWidth() - 1;
        Integer yMax = game.getHeight() - 1;

        List<Cell> cellsAround = getCellsAround(x, y, xMax, yMax);
        List<Cell> minesAround = new ArrayList<>(mines);
        minesAround.retainAll(cellsAround);

        if (minesAround.size() > 0) {
            field.setCellCharValue(x, y, (char) (GameConstants.ZERO_MINE_CHAR + minesAround.size()));
            return cellsNotToProcess;
        }

        field.setCellCharValue(x, y, GameConstants.ZERO_MINE_CHAR);
        for (Cell nearCell : cellsAround) {
            if (field.getCellCharValue(nearCell.getX(), nearCell.getY()) != GameConstants.INITIAL_MINE_CHAR) {
                cellsNotToProcess.add(nearCell);
                continue;
            }
            fillCellValue(game, nearCell, cellsNotToProcess);
        }
        return cellsNotToProcess;
    }

    public static Optional<Cell> getMineForCell(GameTurn gameTurn, Game game) {
        return getMineForCell(game.getField().getMines(), gameTurn.getCol(), gameTurn.getRow());
    }

    private static Optional<Cell> getMineForCell(List<Cell> mines, Integer x, Integer y) {
        return mines.stream().filter(mine -> mine.getX().equals(x) && mine.getY().equals(y)).findFirst();
    }


    private static List<Cell> getCellsAround(Integer x, Integer y, Integer xMax, Integer yMax) {
        List<Cell> cells = new ArrayList<>();
        for (int yCell = y - 1; yCell <= y + 1; yCell++) {
            for (int xCell = x - 1; xCell <= x + 1; xCell++) {
                if (xCell >= 0 && yCell >= 0
                        && xCell <= xMax && yCell <= yMax
                        && !(xCell == x && yCell == y)) {
                    cells.add(new Cell(xCell, yCell));
                }
            }
        }
        return cells;
    }

    public static void fillEntireFieldWithMineValue(Game game, Character mineValue) {
        Field field = game.getField();
        List<Cell> mines = field.getMines();
        List<List<Character>> cells = field.getCells();
        List<Cell> cellsNotToProcess = new ArrayList<>();
        for (int y = 0; y < cells.size(); y++) {
            for (int x = 0; x < cells.get(y).size(); x++) {
                Cell cell = new Cell(x, y);
                if (mines.contains(cell)) {
                    field.setCellCharValue(x, y, mineValue);
                    continue;
                }
                fillCellValue(game, cell, cellsNotToProcess);
            }
        }
    }

}
