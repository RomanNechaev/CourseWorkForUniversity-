package org.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       parseArgs();

       //var game = new Game(11);
//      System.out.println(game.getBoard().getCoordinateIfFirstMove().size());
//        Scanner scanner = new Scanner(System.in);
    //    System.out.println(game.getBoard());
//        System.out.println("Введите координату x");
//        var xStart = scanner.nextInt();
//        System.out.println("Введите координату y");
//        var yStart = scanner.nextInt();
//        var startCoordinate = new Coordinate(xStart, yStart);
//        game.doHumanMove(startCoordinate);
//        System.out.println(game.doAiMove(startCoordinate));
//        System.out.println(game.getPlayer().getScore());
//        while (!game.isGameOver()) {
//            System.out.println("Введите координату x");
//            var x = scanner.nextInt();
//            System.out.println("Введите координату y");
//            var y = scanner.nextInt();
//            var inputCoordinate = new Coordinate(x, y);
//            game.doHumanMove(inputCoordinate);
//            try {
//                System.out.println(game.doAiMove(inputCoordinate));
//            } catch (GameException exception) {
//                System.out.println("Игра закончена победил игрок:");
//            }
//        }
    }

    public static void parseArgs() {
        var scanner = new Scanner(System.in);
        var board_Size = Integer.parseInt(scanner.nextLine());
        var count = 0;
        var result = new ArrayList<String[]>();
        while (count < board_Size) {
            var line = scanner.nextLine().split(" ");
            result.add(line);
            count += 1;
        }
        var board = new Board(result, board_Size);
        var coordinate = scanner.nextLine().split(" ");
        if ("-1".equals(coordinate[0]) && "-1".equals(coordinate[1])) {
            var game = new Game(board_Size);
            System.out.println(getStartCoordinate(board));
            board.makeMove(getStartCoordinate(board));
            while (!game.isGameOver()) {
                var coordinatenew = scanner.nextLine().split(" ");
                game.doHumanMove(new Coordinate(Integer.parseInt(coordinatenew[0]), Integer.parseInt(coordinatenew[1])));
                System.out.println(game.doAiMove(new Coordinate(Integer.parseInt(coordinatenew[0]), Integer.parseInt(coordinatenew[1]))));
            }
        } else {
            var game = new Game(board_Size);
            var startCoordinate = new Coordinate(Integer.parseInt(coordinate[0]), Integer.parseInt(coordinate[1]));
            game.doHumanMove(startCoordinate);
            System.out.println(game.doAiMove(startCoordinate));
            while (!game.isGameOver()) {
                var cordinateInput = scanner.nextLine().split(" ");
                var coord = new Coordinate(Integer.parseInt(cordinateInput[0]), Integer.parseInt(cordinateInput[1]));
                try {
                    game.doHumanMove(coord);
                    System.out.println(game.doAiMove(coord));
                }
                catch (GameException exception) {
                    System.out.println("Игра закончена победил игрок:");
                }

            }
        }
    }

    public static Coordinate getStartCoordinate(Board board) {
        var n = board.getBoardSize() / 2-1;
        var max_value = -1;
        var row = 0;
        var column = 0;
        var tg = new ArrayList<>();
        for (int i = n-1; i <= n+1; i++) {
            for (int j = n-1; j <= n+1; j++) {
                tg.add(board.getCells()[i][j]);
                if (board.getCells()[i][j] > max_value) {
                    max_value = board.getCells()[i][j];
                    row = i;
                    column = j;
                }
            }
        }
        var t = new Coordinate(row, column);
        return t;
    }

}
//4 0 5 9 8 3 7 4 2 5 2
//5 4 5 9 1 5 1 5 1 6 0
//8 5 7 0 4 5 2 5 2 2 3
//7 1 1 1 0 9 5 6 2 4 3
//5 1 9 5 7 8 4 9 8 1 5
//0 2 3 0 6 3 4 6 9 4 2
//7 5 0 7 5 6 4 5 6 4 1
//6 7 1 9 0 4 6 5 0 7 8
//1 8 5 4 2 3 7 7 4 8 4
//9 4 4 1 4 6 8 0 8 3 7
//5 7 3 1 7 4 5 2 1 2 9




