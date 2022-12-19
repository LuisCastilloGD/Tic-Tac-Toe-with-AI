package tictactoe;

import java.util.Random;
import java.util.Scanner;

public class Board{
    public char [][] board;
    private State state;


    enum State{
        X_WIN,
        O_WIN,
        DRAW,
        NOT_FINISHED

    };

    public Board(){
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
        updateStateBoard();

    }
    public Board(String oneLineBoardState){
        int aux = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                char c = oneLineBoardState.charAt(aux);
                switch (c) {
                    case '_' -> board[i][j] = ' ';
                    case 'X' -> board[i][j] = 'X';
                    case 'O' -> board[i][j] = 'O';
                    default -> System.out.println("Error in initial Board");
                }
                aux++;
            }
        }
        updateStateBoard();

    }

    public void printBoard(){
        System.out.println("---------");
        for(int i=0;i<3;i++){
            System.out.print("| ");
            for(int j=0;j<3;j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println("|");
        }
        System.out.println("---------");

    }

    private void inputMove(Move move){
        char moveChar = getNextMoveChar();
        board[move.row][move.col] = moveChar;
    }
    char getNextMoveChar(){
        int countX=0,countO=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                if(board[i][j] == 'X'){
                    countX++;
                }
                if(board[i][j] == 'O'){
                    countO++;
                }
            }
        }

        if(countX==countO){
            return 'X';
        } else if (countX>countO) {
            return 'O';
        }else{
            return 'X';
        }
    }
    protected void updateStateBoard(){

        for (int i = 0; i < 3; i++) {
            //rows
            if(board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i]!=' '){
                if(board[0][i] == 'X'){
                    printBoard();
                    System.out.println("X wins");
                    state = State.X_WIN;
                    cleanBoard();

                }else {
                    printBoard();
                    System.out.println("O wins");
                    state =State.O_WIN;
                    cleanBoard();
                }
                return;

            }
            //columns
            if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0]!=' '){
                if(board[i][0] == 'X'){
                    printBoard();
                    System.out.println("X wins");
                    state= State.X_WIN;
                    cleanBoard();
                }else {
                    printBoard();
                    System.out.println("O wins");
                    state= State.O_WIN;
                    cleanBoard();
                }
                return;

            }
            //Diagonal
            if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0]!=' '){
                if(board[0][0] == 'X'){
                    printBoard();
                    System.out.println("X wins");
                    state= State.X_WIN;
                    cleanBoard();

                }else {
                    printBoard();
                    System.out.println("O wins");
                    state= State.O_WIN;
                    cleanBoard();

                }
                return;
            }
            //Diagonal
            if(board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0]!=' '){
                if(board[2][0] == 'X'){
                    printBoard();
                    System.out.println("X wins");
                    state= State.X_WIN;
                    cleanBoard();

                }else {
                    printBoard();
                    System.out.println("O wins");
                    state= State.O_WIN;
                    cleanBoard();

                }
                return;
            }
        }

        if(isFull()){
            printBoard();
            System.out.println("Draw");
            state= State.DRAW;
            cleanBoard();
        }else{
            state= State.NOT_FINISHED;
        }
    }

    void getUserMove(){

        System.out.println("Enter the coordinates: ");
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            int cordX = scanner.nextInt();
            if(scanner.hasNextInt()){
                int cordY = scanner.nextInt();
                cordY = cordY -1;
                cordX = cordX -1;
                if((cordX >= 0 && cordX<3)&&(cordY >= 0 && cordY<3)){
                    if(validateCord(cordX,cordY,false)){
                        Move cords = new Move(cordX,cordY);
                        inputMove(cords);
                    }else{
                        getUserMove();
                    }
                }else{
                    System.out.println("Coordinates should be from 1 to 3!");
                    getUserMove();
                }

            }else{
                System.out.println("You should enter numbers!");
                getUserMove();
            }
        }else{
            System.out.println("You should enter numbers!");
            getUserMove();
        }


    }

    private void generateRandomMove(){
        Random rand = new Random();
        int cordX = rand.nextInt(3);
        int cordY = rand.nextInt(3);
        int[] cords = {cordX,cordY};
        if(validateCord(cordX,cordY,true)){
            inputMove(new Move(cordX,cordY));
        }else {
            generateRandomMove();
        }
    }
    void generateMoveEasy(){
        System.out.println("Making move level \"easy\"");
        generateRandomMove();
    }

    void generateMoveMedium(){
        char playerChar = getNextMoveChar();
        char enemyChar = getEnemyChar();

        //Search for two in a row to win
        //Search for two in a row for the enemy to stop
        if(analyzeTwoInARow(playerChar)) return;
        else if (analyzeTwoInARow(enemyChar)) return;
        else{
            System.out.println("Making move level \"medium\"");
            generateRandomMove();
        }
    }

    private boolean analyzeTwoInARow(char playerChar){
        //Horizontal two in a row
        for(int i=0;i<3;i++){
            if(board[i][0] == playerChar && board[i][1] == playerChar){
                if(validateCord(i,2,true)){
                    System.out.println("Making move level \"medium\"");
                    inputMove(new Move(i,2));
                    return true;
                }
            }
            else if(board[i][1] == playerChar && board[i][2] == playerChar){
                if(validateCord(i,0,true)){
                    System.out.println("Making move level \"medium\"");
                    inputMove(new Move(i,0));
                    return true;
                }
            }
        }
        //Vertical two in a row
        for(int j=0;j<3;j++){
            if(board[0][j] == playerChar && board[1][j] == playerChar){
                if(validateCord(2,j,true)){
                    System.out.println("Making move level \"medium\"");
                    inputMove(new Move(2,j));
                    return true;
                }
            }
            else if(board[1][j] == playerChar && board[2][j] == playerChar){
                if(validateCord(0,j,true)){
                    System.out.println("Making move level \"medium\"");
                    inputMove(new Move(0,j));
                    return true;
                }
            }
        }
        //Diagonal
        if(board[0][0] == playerChar && board[1][1] == playerChar){
            if(validateCord(2,2,true)){
                System.out.println("Making move level \"medium\"");
                inputMove(new Move(2,2));
                return true;
            }
        }
        else if(board[1][1] == playerChar && board[2][2] == playerChar){
            if(validateCord(0,0,true)){
                System.out.println("Making move level \"medium\"");
                inputMove(new Move(0,0));
                return true;
            }
        }
        if(board[0][2] == playerChar && board[1][1] == playerChar){
            if(validateCord(2,0,true)){
                System.out.println("Making move level \"medium\"");
                inputMove(new Move(2,0));
                return true;
            }
        }
        else if(board[2][0] == playerChar && board[1][1] == playerChar){
            if(validateCord(0,2,true)){
                System.out.println("Making move level \"medium\"");
                inputMove(new Move(0,2));
                return true;
            }
        }
        return false;
    }
    void generateMoveHard(){
        int bestVal = Integer.MIN_VALUE;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        char[][] simulatedBoard = board.clone();
        char player = getNextMoveChar();
        char enemy = getEnemyChar();
        DefaultMinMax minimax = new DefaultMinMax();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(simulatedBoard[i][j]==' '){
                    simulatedBoard[i][j] = player;
                    int score = minimax.calcMinMax(simulatedBoard,0,false,player,enemy);
                    simulatedBoard[i][j] = ' ';
                    if (score > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = score;
                    }

                }
            }
            System.out.println();
        }
        System.out.println("Making move level \"hard\"");
        inputMove(bestMove);

    }

    private boolean validateCord(int cordX, int cordY, boolean isAI){
        if(board[cordX][cordY] == ' '){
            return true;
        }else if(!isAI){
            System.out.println("This cell is occupied! Choose another one!");
            return false;
        }else{
            return false;
        }

    }

    private char getEnemyChar(){
        int countX=0,countO=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                if(board[i][j] == 'X'){
                    countX++;
                }
                if(board[i][j] == 'O'){
                    countO++;
                }
            }
        }

        if(countX==countO){
            return 'O';
        } else if (countX>countO) {
            return 'X';
        }else{
            return 'O';
        }
    }
    
    
    public boolean isFull(){
        int fullCounter = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                if(board[i][j] != ' '){
                    fullCounter++;
                }
            }
        }
        return fullCounter == 9;
    }
    public boolean isFull(char[][]simulatedBoard){
        int fullCounter = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++) {
                if(simulatedBoard[i][j] != ' '){
                    fullCounter++;
                }
            }
        }
        return fullCounter == 9;
    }

    private void cleanBoard(){
        board = new char[][]{
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };
    }



    protected State getState(){
        return state;
    }

}
