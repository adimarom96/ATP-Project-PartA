package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.MazeState;

import java.util.ArrayList;

public class Maze {
    // TODO: make them all private ??!! (and add getters)
    private int numOfRow;
    private int numOfCol;
    int[][] mazeArr;
//todo: remove this function below
    public void setMazeArr(int[][] mazeArr) {
        this.mazeArr = mazeArr;
    }

    private Position StartPosition;
    private Position GoalPosition;

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

    public Boolean possibleToGo(int x, int y) {
        if (mazeArr[x][y] == 0)
            return true;
        return false;
    }

    public void print() {
/*        //TODO: remove index !!!
        System.out.print("   ");
        for (int i = 0; i < numOfRow; i++) {
            System.out.print(" " + i);
        }
        System.out.println("");*/
        for (int i = 0; i < numOfRow; i++) {
            System.out.print(i + " { "); // new row
            for (int j = 0; j < numOfCol; j++) {
                if (this.getStartPosition() != null &&this.getStartPosition().getColumnIndex() == j && this.getStartPosition().getRowIndex() == i)
                    System.out.print("S "); // start point as S
                else if (this.getGoalPosition() != null && this.getGoalPosition().getColumnIndex() == j && this.getGoalPosition().getRowIndex() == i)
                    System.out.print("E "); // end point as E
                else
                    System.out.print(this.mazeArr[i][j] + " ");
            }
            System.out.println("}"); // end of row
        }
    }
}

// new condition :this.getGoalPosition() != null in else if line 64