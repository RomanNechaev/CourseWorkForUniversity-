package org.example;

public enum TurnMove {
    firstPlayer(1),
    secondPlayer(2);

    private final int playerNumber;

    TurnMove(int playerNumber) {
        this.playerNumber = playerNumber;
    }
}
