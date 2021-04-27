package algorithms.mazeGenerators;

import algorithms.search.AState;
import algorithms.search.MazeState;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Maze {
    // TODO: make them all private ??!! (and add getters)
    private int numOfRow;
    private int numOfCol;
    int[][] mazeArr;
    private Position StartPosition;
    private Position GoalPosition;

    // constructor
    public Maze(int row, int col) {
        numOfCol = col;
        numOfRow = row;
        mazeArr = new int[row][col];
    }

    public Maze(byte[] savedMazeBytes) {
        //TODO: implement !
    }

    public int[][] getMazeArr() {
        return mazeArr;
    }

    //todo: remove this function below
    public void setMazeArr(int[][] mazeArr) {
        this.mazeArr = mazeArr;
    }

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

    public int getNumOfRow() {
        return numOfRow;
    }

    public int getNumOfCol() {
        return numOfCol;
    }

    /**
     * @param x - row index.
     * @param y - column index.
     * @return rather the spot (x,y) is reasonable to go - if holds 0.
     */
    public Boolean possibleToGo(int x, int y) {
        if (mazeArr[x][y] == 0)
            return true;
        return false;
    }

    public void print() {
        for (int i = 0; i < numOfRow; i++) {
            System.out.print(" { "); // new row
            for (int j = 0; j < numOfCol; j++) {
                if (this.getStartPosition() != null && this.getStartPosition().getColumnIndex() == j && this.getStartPosition().getRowIndex() == i)
                    System.out.print("S "); // start point as S
                else if (this.getGoalPosition() != null && this.getGoalPosition().getColumnIndex() == j && this.getGoalPosition().getRowIndex() == i)
                    System.out.print("E "); // end point as E
                else
                    System.out.print(this.mazeArr[i][j] + " ");
            }
            System.out.println("}"); // end of row
        }
    }

    /**
     * this function took the maze and shrink it into one byte array.
     * the format is: row, col, X start, Y start, X end, Y end, all the maze
     *
     * @return byte array with all the information of the maze.
     */
    public byte[] toByteArray() {
        // to check use this website: https://cryptii.com/pipes/integer-converter
        int row = getNumOfRow();
        int col = getNumOfCol();
        Position start = getStartPosition();
        Position goal = getGoalPosition();

        int[] data = {row, col, start.getRowIndex(), start.getColumnIndex(), goal.getRowIndex(), goal.getColumnIndex()};

        ByteBuffer byteBuffer = ByteBuffer.allocate(24 + row * col);
        IntBuffer intBuffer = byteBuffer.asIntBuffer();
        intBuffer.put(data);

        byte[] array = byteBuffer.array();

        return array;
    }
}