package org.example;

public class GameStatus {
    private final boolean isOver;

    public GameStatus(boolean isFinished){
        isOver = isFinished;
    }

    public boolean isOver()
    {
        return isOver;
    }
}
