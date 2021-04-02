package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {
        if (row < 2 || col < 2)
            throw new RuntimeException("Wrong parameters !");
        Maze maze = new Maze(row, col);
        Position start = new Position(0, 0);
        Position goal = new Position(row - 1, col - 1);
        maze.setStartPosition(start);
        maze.setGoalPosition(goal);
        maze.mazeArr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                Random r = new Random();
                int rnd = r.nextInt((1 - 0) + 1);
                maze.mazeArr[i][j] = rnd;
            }
        }
        // make one way from start to goal manually
        for (int i = 0; i < row; i++) {
            maze.mazeArr[i][0] = 0;
        }
        for (int i = 0; i < col; i++) {
            maze.mazeArr[row - 1][i] = 0;
        }
        return maze;
    }
}