package org.example;

import java.util.List;

public class AiWithGreedyAlgorithm implements Algorithm {

    //{{0,1},{0,-1},{1,0},{-1,0}};

    @Override
    public Coordinate findBestMovement(Board board, Coordinate coordinate, TurnMove turnMove) {
        List<Coordinate> possibleMoves = board.getPossibleCoordinates(board, coordinate);
        var maxCoordinate = -1;
        Coordinate coordinate1 = null;
        for (Coordinate coord : possibleMoves) {
            if (board.getCellsValue(coord) > maxCoordinate) {
                maxCoordinate = board.getCellsValue(coord);
                coordinate1 = coord;
            }
        }
        return coordinate1;
    }

}
