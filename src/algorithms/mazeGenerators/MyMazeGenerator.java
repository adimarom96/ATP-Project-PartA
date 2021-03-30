package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    private Maze init_with_one(Maze maze) {
        for (int i = 0; i < maze.getNumOfRow(); i++) {
            for (int j = 0; j < maze.getNumOfCol(); j++) {
                maze.mazeArr[i][j] = 1;
            }
        }
        return maze;
    }

    @Override
    public Maze generate(int row, int col) {
        //initialize
        int x, y;
        LinkedList<int[]> designated = new LinkedList<>();// list of arrays that contain the possible path to the next move
        final Random random = new Random();
       /* row = row + 1;
        col = col + 1;*/
        Maze maze = init_with_one(new Maze(row, col));// init with all 1

        x = 0;
        y = random.nextInt(col);

        designated.add(new int[]{x, y, x, y});
        while (!designated.isEmpty()) {
            final int[] f = designated.remove(random.nextInt(designated.size()));
            x = f[2];
            y = f[3];
            if (maze.mazeArr[x][y] == 1) {
                maze.mazeArr[f[0]][f[1]] = maze.mazeArr[x][y] = 0;
                if (x >= 2 && maze.mazeArr[x - 2][y] == 1)
                    designated.add(new int[]{x - 1, y, x - 2, y});
                if (y >= 2 && maze.mazeArr[x][y - 2] == 1)
                    designated.add(new int[]{x, y - 1, x, y - 2});
                if (x < row - 2 && maze.mazeArr[x + 2][y] == 1)
                    designated.add(new int[]{x + 1, y, x + 2, y});
                if (y < col - 2 && maze.mazeArr[x][y + 2] == 1)
                    designated.add(new int[]{x, y + 1, x, y + 2});
            }
        }

        /*
        //slice the array
        int[][] copyarr = new int[row - 1][col - 1];
        for (int i = 0; i < row - 1; i++) {
            for (int j = 0; j < col - 1; j++) {
                copyarr[i][j] = maze.mazeArr[i][j];
            }
        }
        Maze new_maze = new Maze(row - 1, col - 1);
        new_maze.mazeArr = copyarr;*/

        SetPos(maze);
        if (maze.getGoalPosition() == null || maze.getStartPosition() == null) {
            maze = this.generate(row + 1, col + 1); //todo: remove the plus 1
        }
        return maze;
    }

    private void SetPos(Maze maze) {
        for (int i = 0; i < maze.getNumOfCol(); i++) {
            if (maze.mazeArr[0][i] == 0) {
                maze.setStartPosition(new Position(0, i));
                break;
            }
        }
        for (int i = maze.getNumOfCol() - 1; i > 0; i--) {
            if (maze.mazeArr[maze.getNumOfRow() - 1][i] == 0) {
                maze.setGoalPosition(new Position(maze.getNumOfRow() - 1, i));
                break;
            }
        }
    }
}