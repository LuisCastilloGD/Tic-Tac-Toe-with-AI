package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;



public class Main {
    public static void main(String[] args) {

        Board board = new Board();
        Controller gameController = new Controller(board);
        Scanner scanner = new Scanner(System.in);
        boolean active = true;
        String option;

        while (active) {
            System.out.println("Input command: ");
            try {
                option = scanner.nextLine();
                String[] inputs = option.split(" ", 3);
                if (inputs.length == 3 && inputs[0].equals("start")) {
                    if (gameController.setPlayers(inputs[1], inputs[2])) {
                        while (gameController.board.getState() == Board.State.NOT_FINISHED) {
                            gameController.board.printBoard();
                            gameController.nextMove();
                            gameController.board.updateStateBoard();
                        }
                        gameController.board.updateStateBoard();
                    }
                }else {
                    if(option.equals("exit")){
                        active = false;
                    }else{
                        System.out.println("Bad parameters!");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Bad parameters!");
            }
        }
    }
}

