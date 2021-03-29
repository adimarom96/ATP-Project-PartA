package algorithms.maze3D;

import java.util.LinkedList;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator {
    private Maze3D init_with_one(Maze3D maze) {
        for (int z = 0; z < maze.numOfDepth; z++) {
            for (int i = 0; i < maze.numOfRow; i++) {
                for (int j = 0; j < maze.numOfCol; j++) {
                    maze.map[z][i][j] = 1;
                }
            }
        }
        return maze;

    }

    @Override
    public Maze3D generate(int depth, int row, int column) {
        //inistialize
        int z, x, y;
        LinkedList<int[]> designated = new LinkedList<>();// list of arrays that contain the possible path to the next move
        final Random random = new Random();
       /* row = row + 1;
        col = col + 1;*/
        Maze3D maze = init_with_one(new Maze3D(depth, row, column));// init
        z = 0;
        x = 0;
        y = random.nextInt(column);

        designated.add(new int[]{z, x, y, z, x, y});
        while (!designated.isEmpty()) {
            final int[] f = designated.remove(random.nextInt(designated.size()));
            z = f[3];
            x = f[4];
            y = f[5];
            if (maze.map[z][x][y] == 1) {
                maze.map[f[0]][f[1]][f[2]] = maze.map[z][x][y] = 0;
                if (z >= 2 && maze.map[z - 2][x][y] == 1)
                    designated.add(new int[]{z - 1, x, y, z - 2, x, y});

                if (x >= 2 && maze.map[z][x - 2][y] == 1)
                    designated.add(new int[]{z, x - 1, y, z, x - 2, y});

                if (y >= 2 && maze.map[z][x][y - 2] == 1)
                    designated.add(new int[]{z, x, y - 1, z, x, y - 2});

                if (z < depth - 2 && maze.map[z + 2][x][y] == 1)
                    designated.add(new int[]{z + 1, x, y, z + 2, x, y});

                if (x < row - 2 && maze.map[z][x + 2][y] == 1)
                    designated.add(new int[]{z, x + 1, y, z, x + 2, y});

                if (y < column - 2 && maze.map[z][x][y + 2] == 1)
                    designated.add(new int[]{z, x, y + 1, z, x, y + 2});
            }

        }
        SetPos(maze);// may god help us
        /*if(maze.getGoalPosition() == null || maze.getStartPosition() == null) {
            maze = this.generate(row+1,col+1);
        }*/
        return maze;
    }

    private void SetPos(Maze3D maze) {
        for (int i = 0; i < maze.numOfCol; i++) {
            if (maze.map[0][0][i] == 0) {
                maze.setStartPosition(new Position3D(0, 0, i));
                maze.map[0][0][i] = 4;
                break;
            }

        }
        int j = maze.numOfRow-1;
        for (int i = maze.numOfCol - 1; i > 0; i--) {
            if (maze.map[maze.numOfDepth - 1][maze.numOfRow - 1][i] == 0) {
                maze.setGoalPosition(new Position3D(maze.numOfDepth - 1, maze.numOfRow - 1, i));
                maze.map[maze.numOfDepth - 1][maze.numOfRow - 1][i] = 5;
                break;
            }
            if (maze.map[maze.numOfDepth - 1][j][maze.numOfCol-1] == 0) {
                maze.setGoalPosition(new Position3D(maze.numOfDepth - 1, j, maze.numOfCol-1));
                maze.map[maze.numOfDepth - 1][j][maze.numOfCol-1] = 5;
                break;
            }
             j--;


        }
    }
}
