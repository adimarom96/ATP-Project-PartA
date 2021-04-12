package algorithms.mazeGenerators;

import java.util.LinkedList;
import java.util.Random;

public class MyMazeGenerator extends AMazeGenerator {
    /**
     * this function gets maze and set him all to 1, all walls.
     * @param maze
     * @return maze with all 1's
     */
    private Maze init_with_one(Maze maze) {
        for (int i = 0; i < maze.getNumOfRow(); i++) {
            for (int j = 0; j < maze.getNumOfCol(); j++) {
                maze.mazeArr[i][j] = 1;
            }
        }
        return maze;
    }

    /**
     * This function makes way throw all the 1's in the maze. using Prim's algorithm to breaks walls and set them to 0 instead of 1.
     * @param row
     * @param col
     * @return a new maze with possible way from the start to the end point.
     */
    @Override
    public Maze generate(int row, int col) {
        if (row < 2 || col < 2)
            throw new RuntimeException("Wrong parameters !");
        //initialize
        int x, y;
        LinkedList<int[]> designated = new LinkedList<>();// list of arrays that contain the possible path to the next move
        final Random random = new Random();
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
        SetPos(maze); // set start and end points.
        return maze;
    }

    /**
     * This function gets a maze and set his start and end points.
     * @param maze
     */
    private void SetPos(Maze maze) {
        // run throw the first line and look up for the first 0. Then set him to start point.
        for (int i = 0; i < maze.getNumOfCol(); i++) {
            if (maze.mazeArr[0][i] == 0) {
                maze.setStartPosition(new Position(0, i));
                break;
            }
        }
        // run throw the last line and look up for the first 0. Then set him to end point.
        for (int i = maze.getNumOfCol() - 1; i > 0; i--) {
            if (maze.mazeArr[maze.getNumOfRow() - 1][i] == 0) {
                maze.setGoalPosition(new Position(maze.getNumOfRow() - 1, i));
                break;
            }
        }
        // if the last "for" didn't manage to find a 0,
        // run throw the "before last" line and look up for the first 0. Then set him to end point.
        for (int i = maze.getNumOfCol() - 1; i > 0; i--) {
            if (maze.mazeArr[maze.getNumOfRow() - 2][i] == 0) {
                maze.setGoalPosition(new Position(maze.getNumOfRow() - 1, i));
                maze.mazeArr[maze.getNumOfRow() - 1][i] = 0;
                break;
            }
        }
    }
}