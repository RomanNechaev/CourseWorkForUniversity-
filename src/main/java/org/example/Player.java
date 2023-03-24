package org.example;

public class Player {
    private final Board board;

    private final TurnMove turnMove;
    private Algorithm ai;
    private Coordinate startCoordinate;
    private int score;

    Player(Coordinate coordinate, Board board, Algorithm ai, TurnMove playerNumber) {
        this.board = board;
        this.ai = ai;
        this.score = 0;
        this.startCoordinate = coordinate;
        this.turnMove = playerNumber;
    }

    Player(Board board, AiWithMinMax ai, TurnMove playerNumber) {
        this.ai = ai;
        this.board = board;
        this.turnMove = playerNumber;
    }

    public void moveTo(Coordinate coordinate) {
        board.makeMove(coordinate);
    }

    public Coordinate moveToAI(Coordinate coordinate) {
        var bestMovement = ai.findBestMovement(board, coordinate, turnMove);
        board.makeMove(bestMovement);
        return bestMovement;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int value) {
        this.score += value;
    }

    public TurnMove getTurnMove() {
        return turnMove;
    }

    public void decrementPlayerScore(int value) {
        score -= value;
    }
}
