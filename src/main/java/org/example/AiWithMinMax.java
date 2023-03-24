package org.example;

import java.util.List;
import java.util.Random;

public class AiWithMinMax implements Algorithm {

    private TurnMove ourMove;
    private TurnMove opponentMove;

    private Game game;

    AiWithMinMax(Game game) {
        this.game = game;
    }

    @Override
    public Coordinate findBestMovement(Board board, Coordinate coordinate, TurnMove turnMove) {
        ourMove = turnMove;
        opponentMove = turnMove == TurnMove.firstPlayer ? TurnMove.secondPlayer : TurnMove.firstPlayer;
        var bestMovement = minMax(board, coordinate, ourMove, 15, game);
        return bestMovement.getCoordinate();
    }

    private Score minMax(Board board, Coordinate coordinate, TurnMove turnMove, int depth, Game game) {

        int bestScore = (turnMove == ourMove) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Coordinate bestCoordinate = null;
        if (depth == 0 || game.isGameOver()) {
            bestScore = getEvaluation(game);
        } else {
            List<Coordinate> possibleMovement = board.getPossibleCoordinates(board, coordinate);
            for (Coordinate coord : possibleMovement) {
                board.makeMove(coord);
                game.getCurrentPlayer().setScore(board.getCellsValue(coord));
                if (turnMove == ourMove) {
                    if (game.playerAI.getTurnMove() == ourMove)
                        game.turnToTheOppositePlayer(game.playerHuman);
                    if (game.playerHuman.getTurnMove() == ourMove)
                        game.turnToTheOppositePlayer(game.playerAI);
                    int currentScore = minMax(board, coord, opponentMove, depth - 1, game).getScoreCoordinate();
                    if (currentScore > bestScore) {
                        bestScore = currentScore;
                        bestCoordinate = coord;
                    }
                } else {
                    if (game.playerAI.getTurnMove() == opponentMove)
                        game.turnToTheOppositePlayer(game.playerHuman);
                    if (game.playerHuman.getTurnMove() == opponentMove)
                        game.turnToTheOppositePlayer(game.playerAI);
                    int currentScore = minMax(board, coord, ourMove, depth - 1, game).getScoreCoordinate();
                    if (currentScore < bestScore) {
                        bestScore = currentScore;
                        bestCoordinate = coord;
                    }
                }
                board.removeMove(coord);
            }
        }
        return new Score(bestCoordinate, bestScore);
    }

    private int getEvaluation(Game game) {
        return game.getPlayer().getScore();
    }
}
