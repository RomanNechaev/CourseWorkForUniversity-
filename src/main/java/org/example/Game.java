package org.example;

public class Game {
    private Board board;
    public Player playerHuman;
    public Player playerAI;

    private Player nextPlayer;

    private boolean gameOver;

    private AiWithGreedyAlgorithm aiWithGreedyAlgorithm;

    private AiWithMinMax aiWithMinMax;


    public Game(int boardSize) {
        board = new Board(boardSize);
        var startCoordinate = new Coordinate(3, 3);
        this.aiWithGreedyAlgorithm = new AiWithGreedyAlgorithm();
        this.aiWithMinMax = new AiWithMinMax(this);
        playerHuman = new Player(startCoordinate, board, aiWithMinMax, TurnMove.secondPlayer);
        playerAI = new Player(board, aiWithMinMax, TurnMove.firstPlayer);
        this.nextPlayer = playerHuman;
        this.gameOver = false;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer() {
        return nextPlayer;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void doHumanMove(Coordinate coordinate) {
        if (isValidMovement(coordinate) && !board.getPossibleCoordinates(board, coordinate).isEmpty() && isNotLastMovement(coordinate)) {
            playerHuman.moveTo(coordinate);
            playerHuman.setScore(board.getCellsValue(coordinate));
            turnToTheOppositePlayer(playerAI);
        } else {
            gameOver = true;
        }
    }

    public void turnToTheOppositePlayer(Player oppositePlayer) {
        nextPlayer = oppositePlayer;
    }

    public Player getCurrentPlayer() {
        return nextPlayer;
    }

    public Coordinate doAiMove(Coordinate coordinate) {
        if (isNotLastMovement(coordinate)) {
            var bestMovement = playerAI.moveToAI(coordinate);
            if (isValidMovement(bestMovement) && !board.getPossibleCoordinates(board, coordinate).isEmpty() && isNotLastMovement(bestMovement)) {
                playerAI.setScore(board.getCellsValue(bestMovement));
                turnToTheOppositePlayer(playerHuman);
                return bestMovement;
            } else {
                gameOver = true;
                throw new GameException("игра закончена");
            }
        } else {
            gameOver = true;
            throw new GameException("игра закончена");
        }
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public boolean isValidMovement(Coordinate coordinate) {
        return (coordinate.getColumn() != board.getBoardSize() && coordinate.getRow() != board.getBoardSize()
                && coordinate.getColumn() != 0 && coordinate.getRow() != 0);
    }

    public boolean isNotLastMovement(Coordinate coordinate) {
        return ((coordinate.getColumn() != 0 && coordinate.getRow()!=0) && (coordinate.getRow() != board.getBoardSize() - 1 && coordinate.getColumn()!=board.getBoardSize()-1));
    }

}
