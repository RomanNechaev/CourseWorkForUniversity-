package org.example;

public class Score {
    private final Coordinate coordinate;

    private final int scoreCoordinate;

    Score(Coordinate coordinate, int scoreCoordinate) {
        this.coordinate = coordinate;
        this.scoreCoordinate = scoreCoordinate;
    }

    Coordinate getCoordinate() {
        return coordinate;
    }

    int getScoreCoordinate () {
        return scoreCoordinate;
    }
}
