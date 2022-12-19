package tictactoe;

import java.util.HashMap;

import static java.lang.Math.min;
import static java.lang.Math.max;

public class DefaultMinMax implements MinMax{

    @Override
    public int calcMinMax(char[][] simulatedBoard, int depth, boolean isMax,char hardPlayer,char otherPlayer){

        int result = evaluate(simulatedBoard,hardPlayer,otherPlayer);
        if(result==10){
            return result-depth;
        } else if (result==-10) {
            return result -depth;
        }else if(result==0 && !isMovesLeft(simulatedBoard)){
            return result - depth;
        }

        if(isMax){
            int bestScore = -1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (simulatedBoard[i][j]==' ') {
                        simulatedBoard[i][j] = hardPlayer;
                        int score = calcMinMax(simulatedBoard,depth+1,false,hardPlayer,otherPlayer);
                        simulatedBoard[i][j] = ' ';
                        bestScore = max(bestScore,score);
                    }
                }
            }
            return bestScore;
        }else{
            int bestScore = 1000;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (simulatedBoard[i][j]==' ') {
                        simulatedBoard[i][j] = otherPlayer;
                        int score = calcMinMax(simulatedBoard,depth+1,true,hardPlayer,otherPlayer);
                        simulatedBoard[i][j] = ' ';
                        bestScore = min(bestScore,score);
                    }
                }
            }
            return bestScore;
        }

    }

    private static int evaluate(char[][] simulatedBoard,char hardPlayer,char enemy){
        //Horizontal win
        for(int i=0;i<3;i++){
            if(simulatedBoard[i][0] == simulatedBoard[i][1] && simulatedBoard[i][1] == simulatedBoard[i][2]){
                if (simulatedBoard[i][0]==hardPlayer) {
                    return +10;
                } else if (simulatedBoard[i][0]==enemy) {
                    return -10;
                }
            }
        }
        //Vertical win
        for(int j=0;j<3;j++){
            if(simulatedBoard[0][j] == simulatedBoard[1][j] && simulatedBoard[1][j] == simulatedBoard[2][j] ){
                if (simulatedBoard[0][j]==hardPlayer) {
                    return +10;
                } else if (simulatedBoard[0][j]==enemy) {
                    return -10;
                }
            }
        }
        //Diagonal win
        if(simulatedBoard[0][0] == simulatedBoard[1][1] && simulatedBoard[1][1] == simulatedBoard[2][2]){
            if (simulatedBoard[0][0]==hardPlayer) {
                return +10;
            } else if (simulatedBoard[0][0]==enemy) {
                return -10;
            }
        }
        if(simulatedBoard[0][2] == simulatedBoard[1][1] && simulatedBoard[1][1] == simulatedBoard[2][0]){
            if (simulatedBoard[0][2]==hardPlayer) {
                return +10;
            } else if (simulatedBoard[0][2]==enemy) {
                return -10;
            }
        }
        return 0;

    }

    static Boolean isMovesLeft(char[][] board)
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == ' ')
                    return true;
        return false;
    }


}
