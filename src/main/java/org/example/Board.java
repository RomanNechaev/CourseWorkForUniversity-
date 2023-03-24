package org.example;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Board {
    private final int boardSize;
    private final int[][] cells;

    private List<Coordinate> markedMoves = new ArrayList<>();
    private static final Coordinate[] movementCoordinates = {new Coordinate(0, 1), new Coordinate(0, -1), new Coordinate(1, 0),
            new Coordinate(-1, 0)};

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.cells = new int[this.boardSize][this.boardSize];
        var rnd = new Random();
        forEachElement(((row, column) -> {
            cells[row][column] = rnd.nextInt(0, 10);
        }));
    }

    public Board(FileReader rawBoard) {
        JSONParser jsonParser = new JSONParser();
        try {
            var jsonObject = jsonParser.parse(rawBoard).toString().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            jsonObject.remove(0);
            var size = jsonObject.size();
            jsonObject.remove(size - 1);
            var endIndexArray = 0;
            for (int i = 0; i != jsonObject.size(); i++) {
                if (jsonObject.get(i) == ']')
                    break;
                if (jsonObject.get(i) == ',') {
                    endIndexArray += 1;
                }
            }
            this.cells = new int[endIndexArray + 1][endIndexArray + 1];
            jsonObject.removeIf(x -> x == ',' || x == '[' || x == ']');
            for (int i = 0; i < endIndexArray + 1; i++) {
                for (int j = 0; j < endIndexArray + 1; j++) {
                    cells[i % (endIndexArray + 1)][j] = Character.getNumericValue(jsonObject.get(i * (endIndexArray + 1) + j));
                }
            }
            this.boardSize = endIndexArray+1;
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public Board(List<String[]> rawArgs, int boardSize) {
        this.boardSize = boardSize;
        this.cells = new int[boardSize][boardSize];
        for (int i = 0; i < rawArgs.size(); i++) {
            for (int j = 0; j < rawArgs.get(i).length; j++) {
                this.cells[i][j] = Integer.parseInt(rawArgs.get(i)[j]);
            }
        }
    }


    private void forEachElement(Action action) {
        for (int row = 0; row < boardSize; row++) {
            for (int column = 0; column < boardSize; column++) {
                action.doAction(row, column);
            }
        }
    }

    public void makeMove(Coordinate coordinate) {
        if (markedMoves.contains(coordinate))
            throw new GameException("Нельзя выбрать данную клетку! Она уже была помечена!");
        else {
            markedMoves.add(coordinate);
        }
    }


    public int[][] getCells() {
        return cells;
    }


    public void removeMove(Coordinate coordinate) {
        markedMoves.remove(coordinate);
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<Coordinate> getMarkedMoves() {
        return markedMoves;
    }

    public int getCellsValue(Coordinate coordinate) {
        return cells[coordinate.getRow()][coordinate.getColumn()];
    }

    public List<Coordinate> getPossibleCoordinates(Board board, Coordinate coordinate) {
        List<Coordinate> possibleMoves = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            var possibleCoordinate = new Coordinate(coordinate.getRow() + movementCoordinates[i].getRow(),
                    coordinate.getColumn() + movementCoordinates[i].getColumn());
            if (!board.getMarkedMoves().contains(possibleCoordinate) && possibleCoordinate.getColumn()>-1 && possibleCoordinate.getRow()>-1 &&
            possibleCoordinate.getRow()<boardSize && possibleCoordinate.getColumn()<boardSize)
                possibleMoves.add(possibleCoordinate);
        }
        return possibleMoves;
    }

    public List<Coordinate> getCoordinateIfFirstMove() {
        List<Coordinate> coordinates = new ArrayList<>();
        var n = this.boardSize/2-1;
        for (int i = n-1; i <=n+1 ; i++) {
            for (int j = n-1; j <= n+1; j++) {
                coordinates.add(new Coordinate(i,j));
            }
        }
        return coordinates;
    }



    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        forEachElement(((row, column) -> {
            if (row == 0 && column == 0) {
                board.append("  ");
                for (int i = 0; i < boardSize; i++) {
                    board.append(i + " ");
                }
            }
            if (column % boardSize == 0) {
                board.append("\n");
                board.append(row + " ");
            }
            board.append(cells[row][column]);
            board.append(" ");

        }));
        return board.toString();
    }
}
