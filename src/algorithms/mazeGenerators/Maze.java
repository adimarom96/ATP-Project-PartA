package algorithms.mazeGenerators;


import algorithms.search.AState;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class Maze {
    int numOfRow;
    int numOfCol;
    int[][] mazeArr;
    Position StartPosition;
    Position GoalPosition;

    public Position getStartPosition() {
        return StartPosition;
    }

    public Position getGoalPosition() {
        return GoalPosition;
    }

    public void setStartPosition(Position startPosition) {
        StartPosition = startPosition;
    }

    public void setGoalPosition(Position goalPosition) {
        GoalPosition = goalPosition;
    }

    public Maze(int row, int col) {
            numOfCol = col;
            numOfRow = row;
            mazeArr = new int[row][col];
    }

    public int getNumOfRow() {
        return numOfRow;
    }

    public int getNumOfCol() {
        return numOfCol;
    }

    public Boolean possibleToGo(int x , int y){
        if(mazeArr[x][y] == 0 || mazeArr[x][y] == 5)
            return true;
        return  false;
    }

    public void Print() {
        System.out.print("   ");
        for (int i = 0; i < numOfRow; i++) {
            System.out.print(" " + i );
        }
        System.out.println("");
        for (int i = 0; i < numOfRow; i++) {
            System.out.print( i + " | ");
            for (int j = 0; j < numOfCol; j++) {
//
                    System.out.print(this.mazeArr[i][j] + " ");
            }
            System.out.println(); // "\n"
        }
    }


}