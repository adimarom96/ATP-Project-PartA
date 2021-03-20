package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator {

    @Override
    public Maze generate(int row, int col) {

        Maze maze = new Maze(row, col);
        maze.mazeArr = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                //TODO: put some walls somewhere...
                Random r = new Random();
                int rnd =  r.nextInt((1 - 0) + 1) + 0;
                maze.mazeArr[i][j] = rnd;
                System.out.print(rnd + " ");

            }
            System.out.println();
        }
        return  maze;

    }
}
