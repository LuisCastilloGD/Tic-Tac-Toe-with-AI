package tictactoe;

public class Controller {

    public Board board;
    private PlayerType player_O;
    private PlayerType player_X;


    enum PlayerType{
        USER,
        EASY,
        MEDIUM,
        HARD
    }
    Controller(Board board){
        this.board = board;
    }

    public boolean setPlayers(String p1,String p2) {
        switch (p1) {
            case "user" -> player_X = PlayerType.USER;
            case "easy" -> player_X = PlayerType.EASY;
            case "medium" -> player_X = PlayerType.MEDIUM;
            case "hard" -> player_X = PlayerType.HARD;
            default -> {System.out.println("Bad parameters"); return false;}
        }

        switch (p2) {
            case "user" -> player_O = PlayerType.USER;
            case "easy" -> player_O = PlayerType.EASY;
            case "medium" -> player_O = PlayerType.MEDIUM;
            case "hard" -> player_O = PlayerType.HARD;
            default -> {System.out.println("Bad parameters"); return false;}
        }
        return true;

    }

    protected void nextMove(){
        if(board.getNextMoveChar()=='X'){
            switch (this.player_X) {
                case USER -> board.getUserMove();
                case EASY -> board.generateMoveEasy();
                case MEDIUM -> board.generateMoveMedium();
                case HARD -> board.generateMoveHard();
            }
        }else{
            switch (this.player_O) {
                case USER -> board.getUserMove();
                case EASY -> board.generateMoveEasy();
                case MEDIUM -> board.generateMoveMedium();
                case HARD -> board.generateMoveHard();
            }
        }
    }




}
