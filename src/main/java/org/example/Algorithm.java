package org.example;

public interface Algorithm {
    Coordinate findBestMovement(Board board, Coordinate coordinate, TurnMove turnMove);
}
